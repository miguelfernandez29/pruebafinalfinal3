package com.example.app.service;

import com.example.app.dto.AssetCauseDTO;
import com.example.app.entity.AssetCause;
import com.example.app.entity.AssetCauseId;
import com.example.app.repository.AssetCauseRepository;
import com.example.app.repository.GeneralDataRepository;
import com.example.app.entity.GeneralData;
import com.example.app.entity.GeneralDataId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AssetCauseService {

    private final AssetCauseRepository assetCauseRepository;
    private final GeneralDataRepository generalDataRepository;

    public AssetCauseService(AssetCauseRepository assetCauseRepository, GeneralDataRepository generalDataRepository) {
        this.assetCauseRepository = assetCauseRepository;
        this.generalDataRepository = generalDataRepository;
    }

    public List<AssetCauseDTO> findAll() {
        return assetCauseRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<AssetCauseDTO> findById(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, Integer assetSequence) {
        AssetCauseId id = new AssetCauseId(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
        return assetCauseRepository.findById(id).map(this::toDTO);
    }

    public List<AssetCauseDTO> findByDeclaration(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif) {
        return assetCauseRepository.findByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNif(
                presentationYear, taxType, presentationCode, taxpayerNif)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Integer getNextAssetSequence(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif) {
        return assetCauseRepository.findNextAssetSequence(presentationYear, taxType, presentationCode, taxpayerNif);
    }

    public AssetCauseDTO save(AssetCauseDTO dto) {
        validateAssetNatureCode(dto.getAssetNatureCode());
        validateAssetPositionCode(dto.getAssetPositionCode());
        validateTransmissionPercentage(dto.getTransmissionPercentage());

        if (dto.getAssetNatureCode() != null) {
            String description = getGeneralDataDescription("110", dto.getAssetNatureCode());
            dto.setAssetNatureDescription(description);
        }

        if (dto.getAssetPositionCode() != null) {
            String description = getGeneralDataDescription("104", dto.getAssetPositionCode());
            dto.setAssetPositionDescription(description);
        }

        if (dto.getTransmissionPercentage() == null && dto.getAssetNatureCode() != null && dto.getAssetPositionCode() != null) {
            dto.setTransmissionPercentage(new BigDecimal("100"));
        }

        calculateProportionalVerifiedValue(dto);

        AssetCause entity = toEntity(dto);
        AssetCause saved = assetCauseRepository.save(entity);
        return toDTO(saved);
    }

    public void deleteById(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, Integer assetSequence) {
        AssetCauseId id = new AssetCauseId(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
        assetCauseRepository.deleteById(id);
    }

    private void validateAssetNatureCode(String assetNatureCode) {
        if (assetNatureCode == null) {
            throw new IllegalArgumentException("Campo \"Naturaleza\" obligatorio.");
        }
        GeneralDataId id = new GeneralDataId("110", assetNatureCode);
        if (!generalDataRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe el codigo introducido para el campo \"Naturaleza\".");
        }
    }

    private void validateAssetPositionCode(String assetPositionCode) {
        if (assetPositionCode == null) {
            throw new IllegalArgumentException("Campo \"P / G\" obligatorio.");
        }
        GeneralDataId id = new GeneralDataId("104", assetPositionCode);
        if (!generalDataRepository.existsById(id)) {
            throw new IllegalArgumentException("Valores permitidos: P o G.");
        }
    }

    private void validateTransmissionPercentage(BigDecimal transmissionPercentage) {
        if (transmissionPercentage != null) {
            if (transmissionPercentage.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("El campo \"% titularidad\" no puede ser menor o igual al 0%.");
            }
            if (transmissionPercentage.compareTo(new BigDecimal("100")) > 0) {
                throw new IllegalArgumentException("El campo \"% titularidad\" no puede ser superior al 100%.");
            }
        }
    }

    private String getGeneralDataDescription(String dataType, String dataCode) {
        GeneralDataId id = new GeneralDataId(dataType, dataCode);
        return generalDataRepository.findById(id)
                .map(GeneralData::getDescription)
                .orElse(null);
    }

    private void calculateProportionalVerifiedValue(AssetCauseDTO dto) {
        if (dto.getVerifiedValue() != null && dto.getTransmissionPercentage() != null) {
            BigDecimal proportional = dto.getVerifiedValue()
                    .multiply(dto.getTransmissionPercentage())
                    .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
            dto.setProportionalVerifiedValue(proportional);
        }
    }

    private AssetCauseDTO toDTO(AssetCause entity) {
        AssetCauseDTO dto = new AssetCauseDTO();
        dto.setPresentationYear(entity.getPresentationYear());
        dto.setTaxType(entity.getTaxType());
        dto.setPresentationCode(entity.getPresentationCode());
        dto.setTaxpayerNif(entity.getTaxpayerNif());
        dto.setAssetSequence(entity.getAssetSequence());
        dto.setAssetNatureCode(entity.getAssetNatureCode());
        dto.setAssetNatureDescription(entity.getAssetNatureDescription());
        dto.setAssetPositionCode(entity.getAssetPositionCode());
        dto.setAssetPositionDescription(entity.getAssetPositionDescription());
        dto.setTransmissionPercentage(entity.getTransmissionPercentage());
        dto.setDeclaredValue(entity.getDeclaredValue());
        dto.setVerifiedValue(entity.getVerifiedValue());
        dto.setProportionalVerifiedValue(entity.getProportionalVerifiedValue());
        dto.setConformityIndicator(entity.getConformityIndicator());
        dto.setAssetSituationCode(entity.getAssetSituationCode());
        dto.setAccrualDate(entity.getAccrualDate());
        return dto;
    }

    private AssetCause toEntity(AssetCauseDTO dto) {
        AssetCause entity = new AssetCause();
        entity.setPresentationYear(dto.getPresentationYear());
        entity.setTaxType(dto.getTaxType());
        entity.setPresentationCode(dto.getPresentationCode());
        entity.setTaxpayerNif(dto.getTaxpayerNif());
        entity.setAssetSequence(dto.getAssetSequence());
        entity.setAssetNatureCode(dto.getAssetNatureCode());
        entity.setAssetNatureDescription(dto.getAssetNatureDescription());
        entity.setAssetPositionCode(dto.getAssetPositionCode());
        entity.setAssetPositionDescription(dto.getAssetPositionDescription());
        entity.setTransmissionPercentage(dto.getTransmissionPercentage());
        entity.setDeclaredValue(dto.getDeclaredValue());
        entity.setVerifiedValue(dto.getVerifiedValue());
        entity.setProportionalVerifiedValue(dto.getProportionalVerifiedValue());
        entity.setConformityIndicator(dto.getConformityIndicator());
        entity.setAssetSituationCode(dto.getAssetSituationCode());
        entity.setAccrualDate(dto.getAccrualDate());
        return entity;
    }
}