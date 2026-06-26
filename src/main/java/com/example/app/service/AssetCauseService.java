package com.example.app.service;

import com.example.app.dto.AssetCauseDto;
import com.example.app.entity.AssetCause;
import com.example.app.entity.AssetCauseId;
import com.example.app.exception.BusinessValidationException;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.repository.AssetCauseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AssetCauseService {

    private final AssetCauseRepository assetCauseRepository;

    public AssetCauseService(AssetCauseRepository assetCauseRepository) {
        this.assetCauseRepository = assetCauseRepository;
    }

    public List<AssetCauseDto> findAll() {
        return assetCauseRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public AssetCauseDto findById(Integer presentationYear, String taxTypeCode, String presentationCode,
                                   String causeNif, String subCauseCode, Integer assetSequence) {
        AssetCauseId id = new AssetCauseId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        AssetCause entity = assetCauseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AssetCause", "id", id));
        return toDto(entity);
    }

    public List<AssetCauseDto> findByDeclaration(Integer presentationYear, String taxTypeCode,
                                                  String presentationCode, String causeNif, String subCauseCode) {
        return assetCauseRepository.findByPresentationYearAndTaxTypeCodeAndPresentationCodeAndCauseNifAndSubCauseCode(
                presentationYear, taxTypeCode, presentationCode, causeNif, subCauseCode)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public AssetCauseDto create(AssetCauseDto dto) {
        validateAssetCause(dto);
        
        if (dto.getAssetSequence() == null) {
            Integer nextSequence = assetCauseRepository.findNextAssetSequence(
                    dto.getPresentationYear(), dto.getTaxTypeCode(), dto.getPresentationCode(),
                    dto.getCauseNif(), dto.getSubCauseCode());
            dto.setAssetSequence(nextSequence);
        }
        
        AssetCause entity = toEntity(dto);
        calculateProportionalVerifiedValue(entity);
        
        AssetCause saved = assetCauseRepository.save(entity);
        return toDto(saved);
    }

    public AssetCauseDto update(Integer presentationYear, String taxTypeCode, String presentationCode,
                                 String causeNif, String subCauseCode, Integer assetSequence, AssetCauseDto dto) {
        AssetCauseId id = new AssetCauseId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        AssetCause existing = assetCauseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AssetCause", "id", id));
        
        validateAssetCause(dto);
        updateEntityFromDto(existing, dto);
        calculateProportionalVerifiedValue(existing);
        
        AssetCause saved = assetCauseRepository.save(existing);
        return toDto(saved);
    }

    public void delete(Integer presentationYear, String taxTypeCode, String presentationCode,
                       String causeNif, String subCauseCode, Integer assetSequence) {
        AssetCauseId id = new AssetCauseId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        AssetCause existing = assetCauseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AssetCause", "id", id));
        
        if ("S".equals(existing.getReductionIndicator())) {
            throw new BusinessValidationException("REDUCTION_EXISTS", 
                    "Cannot delete asset with linked reductions. Remove reductions first.");
        }
        
        assetCauseRepository.delete(existing);
    }

    public List<AssetCauseDto> findByNatureCode(Integer presentationYear, String taxTypeCode,
                                                 String presentationCode, String causeNif,
                                                 String subCauseCode, String assetNatureCode) {
        return assetCauseRepository.findByPresentationYearAndTaxTypeCodeAndPresentationCodeAndCauseNifAndSubCauseCodeAndAssetNatureCode(
                presentationYear, taxTypeCode, presentationCode, causeNif, subCauseCode, assetNatureCode)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<AssetCauseDto> findAssetsWithReductions(Integer presentationYear, String taxTypeCode,
                                                         String presentationCode, String causeNif,
                                                         String subCauseCode) {
        return assetCauseRepository.findAssetsWithReductions(presentationYear, taxTypeCode,
                presentationCode, causeNif, subCauseCode)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public void updateConformityStatus(Integer presentationYear, String taxTypeCode, String presentationCode,
                                        String causeNif, String subCauseCode, Integer assetSequence,
                                        String conformityIndicator) {
        AssetCauseId id = new AssetCauseId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        AssetCause existing = assetCauseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AssetCause", "id", id));
        
        existing.setConformityIndicator(conformityIndicator);
        
        if ("S".equals(conformityIndicator) && existing.getDeclaredValue() != null) {
            existing.setVerifiedValue(existing.getDeclaredValue());
            calculateProportionalVerifiedValue(existing);
        }
        
        assetCauseRepository.save(existing);
    }

    private void validateAssetCause(AssetCauseDto dto) {
        if (dto.getAssetNatureCode() != null) {
            String natureCode = dto.getAssetNatureCode();
            if (!isValidNatureCode(natureCode)) {
                throw new BusinessValidationException("INVALID_NATURE_CODE", "assetNatureCode",
                        "Asset nature code must be one of: U, R, A, T, C, V, N, G");
            }
        }
        
        if (dto.getAssetPositionCode() != null) {
            String positionCode = dto.getAssetPositionCode();
            if (!"P".equals(positionCode) && !"G".equals(positionCode)) {
                throw new BusinessValidationException("INVALID_POSITION_CODE", "assetPositionCode",
                        "Asset position code must be P or G");
            }
        }
        
        if (dto.getTransmissionPercentage() != null) {
            BigDecimal percentage = dto.getTransmissionPercentage();
            if (percentage.compareTo(BigDecimal.ZERO) <= 0 || percentage.compareTo(new BigDecimal("100")) > 0) {
                throw new BusinessValidationException("INVALID_PERCENTAGE", "transmissionPercentage",
                        "Transmission percentage must be between 0 and 100");
            }
        }
        
        if (dto.getDeclaredValue() != null && dto.getDeclaredValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessValidationException("INVALID_VALUE", "declaredValue",
                    "Declared value must be positive");
        }
    }

    private boolean isValidNatureCode(String code) {
        return "U".equals(code) || "R".equals(code) || "A".equals(code) || "T".equals(code) ||
               "C".equals(code) || "V".equals(code) || "N".equals(code) || "G".equals(code);
    }

    private void calculateProportionalVerifiedValue(AssetCause entity) {
        if (entity.getVerifiedValue() != null && entity.getTransmissionPercentage() != null) {
            BigDecimal proportional = entity.getVerifiedValue()
                    .multiply(entity.getTransmissionPercentage())
                    .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
            entity.setProportionalVerifiedValue(proportional);
        }
    }

    private AssetCauseDto toDto(AssetCause entity) {
        AssetCauseDto dto = new AssetCauseDto();
        dto.setPresentationYear(entity.getPresentationYear());
        dto.setTaxTypeCode(entity.getTaxTypeCode());
        dto.setPresentationCode(entity.getPresentationCode());
        dto.setCauseNif(entity.getCauseNif());
        dto.setSubCauseCode(entity.getSubCauseCode());
        dto.setAssetSequence(entity.getAssetSequence());
        dto.setAssetNatureCode(entity.getAssetNatureCode());
        dto.setAssetPositionCode(entity.getAssetPositionCode());
        dto.setAssetTypeCode(entity.getAssetTypeCode());
        dto.setAssetTypeDescription(entity.getAssetTypeDescription());
        dto.setTransmissionPercentage(entity.getTransmissionPercentage());
        dto.setDeclaredValue(entity.getDeclaredValue());
        dto.setVerifiedValue(entity.getVerifiedValue());
        dto.setProportionalVerifiedValue(entity.getProportionalVerifiedValue());
        dto.setConformityIndicator(entity.getConformityIndicator());
        dto.setReductionIndicator(entity.getReductionIndicator());
        dto.setReferenceValueIndicator(entity.getReferenceValueIndicator());
        dto.setReferenceValue(entity.getReferenceValue());
        dto.setReferenceValueSituation(entity.getReferenceValueSituation());
        dto.setValidReferenceValueIndicator(entity.getValidReferenceValueIndicator());
        dto.setBdbiValueIndicator(entity.getBdbiValueIndicator());
        dto.setBusinessAffectedIndicator(entity.getBusinessAffectedIndicator());
        dto.setReductionAppliedIndicator(entity.getReductionAppliedIndicator());
        return dto;
    }

    private AssetCause toEntity(AssetCauseDto dto) {
        AssetCause entity = new AssetCause();
        entity.setPresentationYear(dto.getPresentationYear());
        entity.setTaxTypeCode(dto.getTaxTypeCode());
        entity.setPresentationCode(dto.getPresentationCode());
        entity.setCauseNif(dto.getCauseNif());
        entity.setSubCauseCode(dto.getSubCauseCode());
        entity.setAssetSequence(dto.getAssetSequence());
        updateEntityFromDto(entity, dto);
        return entity;
    }

    private void updateEntityFromDto(AssetCause entity, AssetCauseDto dto) {
        entity.setAssetNatureCode(dto.getAssetNatureCode());
        entity.setAssetPositionCode(dto.getAssetPositionCode());
        entity.setAssetTypeCode(dto.getAssetTypeCode());
        entity.setAssetTypeDescription(dto.getAssetTypeDescription());
        entity.setTransmissionPercentage(dto.getTransmissionPercentage());
        entity.setDeclaredValue(dto.getDeclaredValue());
        entity.setVerifiedValue(dto.getVerifiedValue());
        entity.setProportionalVerifiedValue(dto.getProportionalVerifiedValue());
        entity.setConformityIndicator(dto.getConformityIndicator());
        entity.setReductionIndicator(dto.getReductionIndicator());
        entity.setReferenceValueIndicator(dto.getReferenceValueIndicator());
        entity.setReferenceValue(dto.getReferenceValue());
        entity.setReferenceValueSituation(dto.getReferenceValueSituation());
        entity.setValidReferenceValueIndicator(dto.getValidReferenceValueIndicator());
        entity.setBdbiValueIndicator(dto.getBdbiValueIndicator());
        entity.setBusinessAffectedIndicator(dto.getBusinessAffectedIndicator());
        entity.setReductionAppliedIndicator(dto.getReductionAppliedIndicator());
    }
}