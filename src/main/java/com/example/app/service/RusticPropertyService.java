package com.example.app.service;

import com.example.app.dto.RusticPropertyDto;
import com.example.app.entity.RusticProperty;
import com.example.app.entity.RusticPropertyId;
import com.example.app.exception.BusinessValidationException;
import com.example.app.exception.InvalidCadastralReferenceException;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.repository.RusticPropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RusticPropertyService {

    private final RusticPropertyRepository rusticPropertyRepository;

    public RusticPropertyService(RusticPropertyRepository rusticPropertyRepository) {
        this.rusticPropertyRepository = rusticPropertyRepository;
    }

    public List<RusticPropertyDto> findAll() {
        return rusticPropertyRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public RusticPropertyDto findById(Integer presentationYear, String taxTypeCode, String presentationCode,
                                       String causeNif, String subCauseCode, Integer assetSequence) {
        RusticPropertyId id = new RusticPropertyId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        RusticProperty entity = rusticPropertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RusticProperty", "id", id));
        return toDto(entity);
    }

    public RusticPropertyDto create(RusticPropertyDto dto) {
        validateRusticProperty(dto);
        RusticProperty entity = toEntity(dto);
        calculateConformity(entity);
        RusticProperty saved = rusticPropertyRepository.save(entity);
        return toDto(saved);
    }

    public RusticPropertyDto update(Integer presentationYear, String taxTypeCode, String presentationCode,
                                     String causeNif, String subCauseCode, Integer assetSequence,
                                     RusticPropertyDto dto) {
        RusticPropertyId id = new RusticPropertyId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        RusticProperty existing = rusticPropertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RusticProperty", "id", id));
        
        validateRusticProperty(dto);
        updateEntityFromDto(existing, dto);
        calculateConformity(existing);
        
        RusticProperty saved = rusticPropertyRepository.save(existing);
        return toDto(saved);
    }

    public void delete(Integer presentationYear, String taxTypeCode, String presentationCode,
                       String causeNif, String subCauseCode, Integer assetSequence) {
        RusticPropertyId id = new RusticPropertyId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        RusticProperty existing = rusticPropertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RusticProperty", "id", id));
        rusticPropertyRepository.delete(existing);
    }

    public List<RusticPropertyDto> findByCadastralReference(String cadastralReference) {
        return rusticPropertyRepository.findByCadastralReference(cadastralReference).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<RusticPropertyDto> findByLocation(String provinceCode, String municipalityCode,
                                                   String polygon, String parcel) {
        return rusticPropertyRepository.findByLocation(provinceCode, municipalityCode, polygon, parcel).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private void validateRusticProperty(RusticPropertyDto dto) {
        if (dto.getCadastralReference() != null) {
            String ref = dto.getCadastralReference().replaceAll("\\s+", "");
            if (ref.length() < 14 || ref.length() > 20) {
                throw new InvalidCadastralReferenceException(dto.getCadastralReference(),
                        "14-20 alphanumeric characters");
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
        
        if (dto.getSurfaceArea() != null && dto.getSurfaceArea().compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessValidationException("INVALID_SURFACE", "surfaceArea",
                    "Surface area must be positive");
        }
        
        if (dto.getDeclaredValue() != null && dto.getDeclaredValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessValidationException("INVALID_VALUE", "declaredValue",
                    "Declared value must be positive");
        }
    }

    private void calculateConformity(RusticProperty entity) {
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

    private RusticPropertyDto toDto(RusticProperty entity) {
        RusticPropertyDto dto = new RusticPropertyDto();
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
        dto.setLocationName(entity.getLocationName());
        dto.setPolygon(entity.getPolygon());
        dto.setParcel(entity.getParcel());
        dto.setSurfaceArea(entity.getSurfaceArea());
        dto.setDeclaredValue(entity.getDeclaredValue());
        dto.setVerifiedValue(entity.getVerifiedValue());
        dto.setReferenceValue(entity.getReferenceValue());
        dto.setConformityIndicator(entity.getConformityIndicator());
        dto.setReferenceValueSituation(entity.getReferenceValueSituation());
        dto.setReferenceValueIndicator(entity.getReferenceValueIndicator());
        dto.setValidReferenceValueIndicator(entity.getValidReferenceValueIndicator());
        dto.setBdbiValueIndicator(entity.getBdbiValueIndicator());
        dto.setCadastralValue(entity.getCadastralValue());
        dto.setParticipationPercentage(entity.getParticipationPercentage());
        return dto;
    }

    private RusticProperty toEntity(RusticPropertyDto dto) {
        RusticProperty entity = new RusticProperty();
        entity.setPresentationYear(dto.getPresentationYear());
        entity.setTaxTypeCode(dto.getTaxTypeCode());
        entity.setPresentationCode(dto.getPresentationCode());
        entity.setCauseNif(dto.getCauseNif());
        entity.setSubCauseCode(dto.getSubCauseCode());
        entity.setAssetSequence(dto.getAssetSequence());
        updateEntityFromDto(entity, dto);
        return entity;
    }

    private void updateEntityFromDto(RusticProperty entity, RusticPropertyDto dto) {
        entity.setPropertyTypeCode(dto.getPropertyTypeCode());
        entity.setPropertyTypeDescription(dto.getPropertyTypeDescription());
        entity.setCadastralReference(dto.getCadastralReference());
        entity.setProvinceCode(dto.getProvinceCode());
        entity.setProvinceDescription(dto.getProvinceDescription());
        entity.setMunicipalityCode(dto.getMunicipalityCode());
        entity.setMunicipalityDescription(dto.getMunicipalityDescription());
        entity.setLocationName(dto.getLocationName());
        entity.setPolygon(dto.getPolygon());
        entity.setParcel(dto.getParcel());
        entity.setSurfaceArea(dto.getSurfaceArea());
        entity.setDeclaredValue(dto.getDeclaredValue());
        entity.setVerifiedValue(dto.getVerifiedValue());
        entity.setReferenceValue(dto.getReferenceValue());
        entity.setConformityIndicator(dto.getConformityIndicator());
        entity.setReferenceValueSituation(dto.getReferenceValueSituation());
        entity.setReferenceValueIndicator(dto.getReferenceValueIndicator());
        entity.setValidReferenceValueIndicator(dto.getValidReferenceValueIndicator());
        entity.setBdbiValueIndicator(dto.getBdbiValueIndicator());
        entity.setCadastralValue(dto.getCadastralValue());
        entity.setParticipationPercentage(dto.getParticipationPercentage());
    }
}