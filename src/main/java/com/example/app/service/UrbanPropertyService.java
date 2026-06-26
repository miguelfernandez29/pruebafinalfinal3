package com.example.app.service;

import com.example.app.dto.UrbanPropertyDto;
import com.example.app.entity.UrbanProperty;
import com.example.app.entity.UrbanPropertyId;
import com.example.app.exception.BusinessValidationException;
import com.example.app.exception.InvalidCadastralReferenceException;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.repository.UrbanPropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UrbanPropertyService {

    private final UrbanPropertyRepository urbanPropertyRepository;

    public UrbanPropertyService(UrbanPropertyRepository urbanPropertyRepository) {
        this.urbanPropertyRepository = urbanPropertyRepository;
    }

    public List<UrbanPropertyDto> findAll() {
        return urbanPropertyRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public UrbanPropertyDto findById(Integer presentationYear, String taxTypeCode, String presentationCode,
                                      String causeNif, String subCauseCode, Integer assetSequence) {
        UrbanPropertyId id = new UrbanPropertyId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        UrbanProperty entity = urbanPropertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UrbanProperty", "id", id));
        return toDto(entity);
    }

    public UrbanPropertyDto create(UrbanPropertyDto dto) {
        validateUrbanProperty(dto);
        UrbanProperty entity = toEntity(dto);
        calculateConformity(entity);
        UrbanProperty saved = urbanPropertyRepository.save(entity);
        return toDto(saved);
    }

    public UrbanPropertyDto update(Integer presentationYear, String taxTypeCode, String presentationCode,
                                    String causeNif, String subCauseCode, Integer assetSequence,
                                    UrbanPropertyDto dto) {
        UrbanPropertyId id = new UrbanPropertyId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        UrbanProperty existing = urbanPropertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UrbanProperty", "id", id));
        
        validateUrbanProperty(dto);
        updateEntityFromDto(existing, dto);
        calculateConformity(existing);
        
        UrbanProperty saved = urbanPropertyRepository.save(existing);
        return toDto(saved);
    }

    public void delete(Integer presentationYear, String taxTypeCode, String presentationCode,
                       String causeNif, String subCauseCode, Integer assetSequence) {
        UrbanPropertyId id = new UrbanPropertyId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        UrbanProperty existing = urbanPropertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UrbanProperty", "id", id));
        urbanPropertyRepository.delete(existing);
    }

    public List<UrbanPropertyDto> findByCadastralReference(String cadastralReference) {
        return urbanPropertyRepository.findByCadastralReference(cadastralReference).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<UrbanPropertyDto> findByProvinceAndMunicipality(String provinceCode, String municipalityCode) {
        return urbanPropertyRepository.findByProvinceAndMunicipality(provinceCode, municipalityCode).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private void validateUrbanProperty(UrbanPropertyDto dto) {
        if (dto.getCadastralReference() != null) {
            String ref = dto.getCadastralReference().replaceAll("\\s+", "");
            if (ref.length() != 20) {
                throw new InvalidCadastralReferenceException(dto.getCadastralReference(),
                        "20 alphanumeric characters");
            }
        }
        
        if (dto.getProvinceCode() != null && dto.getProvinceCode().length() > 2) {
            throw new BusinessValidationException("INVALID_PROVINCE", "provinceCode",
                    "Province code must be 2 characters");
        }
        
        if (dto.getMunicipalityCode() != null && dto.getMunicipalityCode().length() > 3) {
            throw new BusinessValidationException("INVALID_MUNICIPALITY", "municipalityCode",
                    "Municipality code must be 3 characters");
        }
        
        if (dto.getConstructionYear() != null) {
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            if (dto.getConstructionYear() < 1500 || dto.getConstructionYear() > currentYear) {
                throw new BusinessValidationException("INVALID_YEAR", "constructionYear",
                        "Construction year must be between 1500 and current year");
            }
        }
        
        if (dto.getRentalIndicator() != null) {
            if (!"S".equals(dto.getRentalIndicator()) && !"N".equals(dto.getRentalIndicator())) {
                throw new BusinessValidationException("INVALID_INDICATOR", "rentalIndicator",
                        "Rental indicator must be S or N");
            }
        }
        
        if (dto.getOfficialProtectionIndicator() != null) {
            if (!"S".equals(dto.getOfficialProtectionIndicator()) && 
                !"N".equals(dto.getOfficialProtectionIndicator())) {
                throw new BusinessValidationException("INVALID_INDICATOR", "officialProtectionIndicator",
                        "Official protection indicator must be S or N");
            }
        }
        
        if (dto.getDeclaredValue() != null && dto.getDeclaredValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessValidationException("INVALID_VALUE", "declaredValue",
                    "Declared value must be positive");
        }
        
        if (dto.getPostalCode() != null && dto.getProvinceCode() != null) {
            if (!dto.getPostalCode().startsWith(dto.getProvinceCode())) {
                throw new BusinessValidationException("INVALID_POSTAL_CODE", "postalCode",
                        "Postal code must start with province code");
            }
        }
    }

    private void calculateConformity(UrbanProperty entity) {
        if ("S".equals(entity.getReferenceValueIndicator()) &&
            "S".equals(entity.getValidReferenceValueIndicator()) &&
            entity.getReferenceValue() != null &&
            entity.getDeclaredValue() != null) {
            
            if (entity.getDeclaredValue().compareTo(entity.getReferenceValue()) >= 0) {
                entity.setConformityIndicator("S");
                entity.setVerifiedValue(entity.getDeclaredValue());
            } else {
                entity.setConformityIndicator("N");
                if (entity.getVerifiedValue() == null) {
                    entity.setVerifiedValue(entity.getReferenceValue());
                }
            }
        }
    }

    private UrbanPropertyDto toDto(UrbanProperty entity) {
        UrbanPropertyDto dto = new UrbanPropertyDto();
        dto.setPresentationYear(entity.getPresentationYear());
        dto.setTaxTypeCode(entity.getTaxTypeCode());
        dto.setPresentationCode(entity.getPresentationCode());
        dto.setCauseNif(entity.getCauseNif());
        dto.setSubCauseCode(entity.getSubCauseCode());
        dto.setAssetSequence(entity.getAssetSequence());
        dto.setPropertyTypeCode(entity.getPropertyTypeCode());
        dto.setPropertyTypeDescription(entity.getPropertyTypeDescription());
        dto.setCadastralReference(entity.getCadastralReference());
        dto.setProvinceCode(entity.getProvinceCode());
        dto.setProvinceDescription(entity.getProvinceDescription());
        dto.setMunicipalityCode(entity.getMunicipalityCode());
        dto.setMunicipalityDescription(entity.getMunicipalityDescription());
        dto.setPostalCode(entity.getPostalCode());
        dto.setStreetTypeCode(entity.getStreetTypeCode());
        dto.setStreetTypeDescription(entity.getStreetTypeDescription());
        dto.setStreetName(entity.getStreetName());
        dto.setStreetNumber(entity.getStreetNumber());
        dto.setStairway(entity.getStairway());
        dto.setFloor(entity.getFloor());
        dto.setDoor(entity.getDoor());
        dto.setConstructionYear(entity.getConstructionYear());
        dto.setConstructedSurface(entity.getConstructedSurface());
        dto.setLandSurface(entity.getLandSurface());
        dto.setRentalIndicator(entity.getRentalIndicator());
        dto.setRentalContractYear(entity.getRentalContractYear());
        dto.setOfficialProtectionIndicator(entity.getOfficialProtectionIndicator());
        dto.setMaxSalePrice(entity.getMaxSalePrice());
        dto.setDeclaredValue(entity.getDeclaredValue());
        dto.setVerifiedValue(entity.getVerifiedValue());
        dto.setReferenceValue(entity.getReferenceValue());
        dto.setConformityIndicator(entity.getConformityIndicator());
        dto.setReferenceValueSituation(entity.getReferenceValueSituation());
        dto.setReferenceValueIndicator(entity.getReferenceValueIndicator());
        dto.setValidReferenceValueIndicator(entity.getValidReferenceValueIndicator());
        dto.setBdbiValueIndicator(entity.getBdbiValueIndicator());
        dto.setUrbanZoneCode(entity.getUrbanZoneCode());
        dto.setSectorCode(entity.getSectorCode());
        dto.setCadastralValue(entity.getCadastralValue());
        dto.setParticipationPercentage(entity.getParticipationPercentage());
        return dto;
    }

    private UrbanProperty toEntity(UrbanPropertyDto dto) {
        UrbanProperty entity = new UrbanProperty();
        entity.setPresentationYear(dto.getPresentationYear());
        entity.setTaxTypeCode(dto.getTaxTypeCode());
        entity.setPresentationCode(dto.getPresentationCode());
        entity.setCauseNif(dto.getCauseNif());
        entity.setSubCauseCode(dto.getSubCauseCode());
        entity.setAssetSequence(dto.getAssetSequence());
        updateEntityFromDto(entity, dto);
        return entity;
    }

    private void updateEntityFromDto(UrbanProperty entity, UrbanPropertyDto dto) {
        entity.setPropertyTypeCode(dto.getPropertyTypeCode());
        entity.setPropertyTypeDescription(dto.getPropertyTypeDescription());
        entity.setCadastralReference(dto.getCadastralReference());
        entity.setProvinceCode(dto.getProvinceCode());
        entity.setProvinceDescription(dto.getProvinceDescription());
        entity.setMunicipalityCode(dto.getMunicipalityCode());
        entity.setMunicipalityDescription(dto.getMunicipalityDescription());
        entity.setPostalCode(dto.getPostalCode());
        entity.setStreetTypeCode(dto.getStreetTypeCode());
        entity.setStreetTypeDescription(dto.getStreetTypeDescription());
        entity.setStreetName(dto.getStreetName());
        entity.setStreetNumber(dto.getStreetNumber());
        entity.setStairway(dto.getStairway());
        entity.setFloor(dto.getFloor());
        entity.setDoor(dto.getDoor());
        entity.setConstructionYear(dto.getConstructionYear());
        entity.setConstructedSurface(dto.getConstructedSurface());
        entity.setLandSurface(dto.getLandSurface());
        entity.setRentalIndicator(dto.getRentalIndicator());
        entity.setRentalContractYear(dto.getRentalContractYear());
        entity.setOfficialProtectionIndicator(dto.getOfficialProtectionIndicator());
        entity.setMaxSalePrice(dto.getMaxSalePrice());
        entity.setDeclaredValue(dto.getDeclaredValue());
        entity.setVerifiedValue(dto.getVerifiedValue());
        entity.setReferenceValue(dto.getReferenceValue());
        entity.setConformityIndicator(dto.getConformityIndicator());
        entity.setReferenceValueSituation(dto.getReferenceValueSituation());
        entity.setReferenceValueIndicator(dto.getReferenceValueIndicator());
        entity.setValidReferenceValueIndicator(dto.getValidReferenceValueIndicator());
        entity.setBdbiValueIndicator(dto.getBdbiValueIndicator());
        entity.setUrbanZoneCode(dto.getUrbanZoneCode());
        entity.setSectorCode(dto.getSectorCode());
        entity.setCadastralValue(dto.getCadastralValue());
        entity.setParticipationPercentage(dto.getParticipationPercentage());
    }
}