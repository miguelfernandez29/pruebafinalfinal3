package com.example.app.service;

import com.example.app.dto.BusinessAssetDto;
import com.example.app.entity.BusinessAsset;
import com.example.app.entity.BusinessAssetId;
import com.example.app.exception.BusinessValidationException;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.repository.BusinessAssetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BusinessAssetService {

    private final BusinessAssetRepository businessAssetRepository;

    public BusinessAssetService(BusinessAssetRepository businessAssetRepository) {
        this.businessAssetRepository = businessAssetRepository;
    }

    public List<BusinessAssetDto> findAll() {
        return businessAssetRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public BusinessAssetDto findById(Integer presentationYear, String taxTypeCode, String presentationCode,
                                      String causeNif, String subCauseCode, Integer assetSequence) {
        BusinessAssetId id = new BusinessAssetId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        BusinessAsset entity = businessAssetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BusinessAsset", "id", id));
        return toDto(entity);
    }

    public BusinessAssetDto create(BusinessAssetDto dto) {
        validateBusinessAsset(dto);
        BusinessAsset entity = toEntity(dto);
        BusinessAsset saved = businessAssetRepository.save(entity);
        return toDto(saved);
    }

    public BusinessAssetDto update(Integer presentationYear, String taxTypeCode, String presentationCode,
                                    String causeNif, String subCauseCode, Integer assetSequence,
                                    BusinessAssetDto dto) {
        BusinessAssetId id = new BusinessAssetId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        BusinessAsset existing = businessAssetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BusinessAsset", "id", id));
        
        validateBusinessAsset(dto);
        updateEntityFromDto(existing, dto);
        
        BusinessAsset saved = businessAssetRepository.save(existing);
        return toDto(saved);
    }

    public void delete(Integer presentationYear, String taxTypeCode, String presentationCode,
                       String causeNif, String subCauseCode, Integer assetSequence) {
        BusinessAssetId id = new BusinessAssetId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        BusinessAsset existing = businessAssetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BusinessAsset", "id", id));
        businessAssetRepository.delete(existing);
    }

    public List<BusinessAssetDto> findByCompanyNif(String companyNif) {
        return businessAssetRepository.findByCompanyNif(companyNif).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<BusinessAssetDto> findBusinessAffectedAssets() {
        return businessAssetRepository.findBusinessAffectedAssets().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private void validateBusinessAsset(BusinessAssetDto dto) {
        if (dto.getCompanyNif() != null && dto.getCompanyNif().length() > 9) {
            throw new BusinessValidationException("INVALID_NIF", "companyNif",
                    "Company NIF must be 9 characters or less");
        }
        
        if (dto.getBusinessAffectedIndicator() != null) {
            if (!"S".equals(dto.getBusinessAffectedIndicator()) && 
                !"N".equals(dto.getBusinessAffectedIndicator())) {
                throw new BusinessValidationException("INVALID_INDICATOR", "businessAffectedIndicator",
                        "Business affected indicator must be S or N");
            }
        }
        
        if (dto.getReductionIndicator() != null) {
            if (!"S".equals(dto.getReductionIndicator()) && !"N".equals(dto.getReductionIndicator())) {
                throw new BusinessValidationException("INVALID_INDICATOR", "reductionIndicator",
                        "Reduction indicator must be S or N");
            }
        }
        
        if (dto.getDeclaredValue() != null && dto.getDeclaredValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessValidationException("INVALID_VALUE", "declaredValue",
                    "Declared value must be positive");
        }
        
        if (dto.getParticipationPercentage() != null) {
            if (dto.getParticipationPercentage().compareTo(BigDecimal.ZERO) <= 0 ||
                dto.getParticipationPercentage().compareTo(new BigDecimal("100")) > 0) {
                throw new BusinessValidationException("INVALID_PERCENTAGE", "participationPercentage",
                        "Participation percentage must be between 0 and 100");
            }
        }
    }

    private BusinessAssetDto toDto(BusinessAsset entity) {
        BusinessAssetDto dto = new BusinessAssetDto();
        dto.setPresentationYear(entity.getPresentationYear());
        dto.setTaxTypeCode(entity.getTaxTypeCode());
        dto.setPresentationCode(entity.getPresentationCode());
        dto.setCauseNif(entity.getCauseNif());
        dto.setSubCauseCode(entity.getSubCauseCode());
        dto.setAssetSequence(entity.getAssetSequence());
        dto.setAssetTypeCode(entity.getAssetTypeCode());
        dto.setAssetTypeDescription(entity.getAssetTypeDescription());
        dto.setCompanyNif(entity.getCompanyNif());
        dto.setCompanyName(entity.getCompanyName());
        dto.setProvinceCode(entity.getProvinceCode());
        dto.setProvinceDescription(entity.getProvinceDescription());
        dto.setMunicipalityCode(entity.getMunicipalityCode());
        dto.setMunicipalityDescription(entity.getMunicipalityDescription());
        dto.setPostalCode(entity.getPostalCode());
        dto.setStreetTypeCode(entity.getStreetTypeCode());
        dto.setStreetTypeDescription(entity.getStreetTypeDescription());
        dto.setStreetName(entity.getStreetName());
        dto.setStreetNumber(entity.getStreetNumber());
        dto.setDescription(entity.getDescription());
        dto.setBusinessAffectedIndicator(entity.getBusinessAffectedIndicator());
        dto.setReductionIndicator(entity.getReductionIndicator());
        dto.setDeclaredValue(entity.getDeclaredValue());
        dto.setVerifiedValue(entity.getVerifiedValue());
        dto.setParticipationPercentage(entity.getParticipationPercentage());
        return dto;
    }

    private BusinessAsset toEntity(BusinessAssetDto dto) {
        BusinessAsset entity = new BusinessAsset();
        entity.setPresentationYear(dto.getPresentationYear());
        entity.setTaxTypeCode(dto.getTaxTypeCode());
        entity.setPresentationCode(dto.getPresentationCode());
        entity.setCauseNif(dto.getCauseNif());
        entity.setSubCauseCode(dto.getSubCauseCode());
        entity.setAssetSequence(dto.getAssetSequence());
        updateEntityFromDto(entity, dto);
        return entity;
    }

    private void updateEntityFromDto(BusinessAsset entity, BusinessAssetDto dto) {
        entity.setAssetTypeCode(dto.getAssetTypeCode());
        entity.setAssetTypeDescription(dto.getAssetTypeDescription());
        entity.setCompanyNif(dto.getCompanyNif());
        entity.setCompanyName(dto.getCompanyName());
        entity.setProvinceCode(dto.getProvinceCode());
        entity.setProvinceDescription(dto.getProvinceDescription());
        entity.setMunicipalityCode(dto.getMunicipalityCode());
        entity.setMunicipalityDescription(dto.getMunicipalityDescription());
        entity.setPostalCode(dto.getPostalCode());
        entity.setStreetTypeCode(dto.getStreetTypeCode());
        entity.setStreetTypeDescription(dto.getStreetTypeDescription());
        entity.setStreetName(dto.getStreetName());
        entity.setStreetNumber(dto.getStreetNumber());
        entity.setDescription(dto.getDescription());
        entity.setBusinessAffectedIndicator(dto.getBusinessAffectedIndicator());
        entity.setReductionIndicator(dto.getReductionIndicator());
        entity.setDeclaredValue(dto.getDeclaredValue());
        entity.setVerifiedValue(dto.getVerifiedValue());
        entity.setParticipationPercentage(dto.getParticipationPercentage());
    }
}