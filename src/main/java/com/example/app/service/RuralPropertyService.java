package com.example.app.service;

import com.example.app.dto.RuralPropertyDTO;
import com.example.app.entity.RuralProperty;
import com.example.app.entity.RuralPropertyId;
import com.example.app.repository.RuralPropertyRepository;
import com.example.app.repository.ProvinceRepository;
import com.example.app.repository.MunicipalityRepository;
import com.example.app.repository.PropertyTypeRepository;
import com.example.app.entity.MunicipalityId;
import com.example.app.entity.PropertyTypeId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RuralPropertyService {

    private final RuralPropertyRepository ruralPropertyRepository;
    private final ProvinceRepository provinceRepository;
    private final MunicipalityRepository municipalityRepository;
    private final PropertyTypeRepository propertyTypeRepository;

    public RuralPropertyService(RuralPropertyRepository ruralPropertyRepository,
                                ProvinceRepository provinceRepository,
                                MunicipalityRepository municipalityRepository,
                                PropertyTypeRepository propertyTypeRepository) {
        this.ruralPropertyRepository = ruralPropertyRepository;
        this.provinceRepository = provinceRepository;
        this.municipalityRepository = municipalityRepository;
        this.propertyTypeRepository = propertyTypeRepository;
    }

    public List<RuralPropertyDTO> findAll() {
        return ruralPropertyRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<RuralPropertyDTO> findById(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, Integer assetSequence) {
        RuralPropertyId id = new RuralPropertyId(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
        return ruralPropertyRepository.findById(id).map(this::toDTO);
    }

    public List<RuralPropertyDTO> findByDeclaration(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif) {
        return ruralPropertyRepository.findByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNif(
                presentationYear, taxType, presentationCode, taxpayerNif)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public RuralPropertyDTO save(RuralPropertyDTO dto) {
        validateProvinceCode(dto.getProvinceCode());
        validateMunicipalityCode(dto.getProvinceCode(), dto.getMunicipalityCode());
        validatePropertyTypeCode(dto.getPropertyTypeCode());
        validateCadastralReference(dto.getCadastralReference());
        validateSurfaceArea(dto.getSurfaceArea());

        populateDescriptions(dto);
        calculateConformity(dto);

        RuralProperty entity = toEntity(dto);
        RuralProperty saved = ruralPropertyRepository.save(entity);
        return toDTO(saved);
    }

    public void deleteById(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, Integer assetSequence) {
        RuralPropertyId id = new RuralPropertyId(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
        ruralPropertyRepository.deleteById(id);
    }

    private void validateProvinceCode(String provinceCode) {
        if (provinceCode != null && !provinceRepository.existsById(provinceCode)) {
            throw new IllegalArgumentException("Provincia no encontrada.");
        }
    }

    private void validateMunicipalityCode(String provinceCode, String municipalityCode) {
        if (municipalityCode != null && provinceCode != null) {
            MunicipalityId id = new MunicipalityId(provinceCode, municipalityCode);
            if (!municipalityRepository.existsById(id)) {
                throw new IllegalArgumentException("Municipio no encontrado para la provincia indicada.");
            }
        }
    }

    private void validatePropertyTypeCode(String propertyTypeCode) {
        if (propertyTypeCode != null) {
            PropertyTypeId id = new PropertyTypeId("R", propertyTypeCode);
            if (!propertyTypeRepository.existsById(id)) {
                throw new IllegalArgumentException("Tipo de bien no encontrado.");
            }
        }
    }

    private void validateCadastralReference(String cadastralReference) {
        if (cadastralReference != null) {
            String cleaned = cadastralReference.trim().replace(" ", "");
            if (cleaned.length() != 14 && cleaned.length() != 20) {
                throw new IllegalArgumentException("Referencia catastral no valida.");
            }
        }
    }

    private void validateSurfaceArea(BigDecimal surfaceArea) {
        if (surfaceArea != null && surfaceArea.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("La superficie debe ser positiva.");
        }
    }

    private void populateDescriptions(RuralPropertyDTO dto) {
        if (dto.getProvinceCode() != null) {
            provinceRepository.findById(dto.getProvinceCode())
                    .ifPresent(p -> dto.setProvinceDescription(p.getProvinceDescription()));
        }
        if (dto.getProvinceCode() != null && dto.getMunicipalityCode() != null) {
            MunicipalityId mId = new MunicipalityId(dto.getProvinceCode(), dto.getMunicipalityCode());
            municipalityRepository.findById(mId)
                    .ifPresent(m -> dto.setMunicipalityDescription(m.getMunicipalityDescription()));
        }
        if (dto.getPropertyTypeCode() != null) {
            PropertyTypeId ptId = new PropertyTypeId("R", dto.getPropertyTypeCode());
            propertyTypeRepository.findById(ptId)
                    .ifPresent(pt -> dto.setPropertyTypeDescription(pt.getPropertyTypeDescription()));
        }
    }

    private void calculateConformity(RuralPropertyDTO dto) {
        if (dto.getReferenceValue() != null && dto.getDeclaredValue() != null) {
            if (dto.getDeclaredValue().compareTo(dto.getReferenceValue()) >= 0) {
                dto.setConformityIndicator("S");
                dto.setVerifiedValue(dto.getDeclaredValue());
            } else {
                dto.setConformityIndicator("N");
                if (dto.getVerifiedValue() == null) {
                    dto.setVerifiedValue(dto.getReferenceValue());
                }
            }
        }
    }

    private RuralPropertyDTO toDTO(RuralProperty entity) {
        RuralPropertyDTO dto = new RuralPropertyDTO();
        dto.setPresentationYear(entity.getPresentationYear());
        dto.setTaxType(entity.getTaxType());
        dto.setPresentationCode(entity.getPresentationCode());
        dto.setTaxpayerNif(entity.getTaxpayerNif());
        dto.setAssetSequence(entity.getAssetSequence());
        dto.setProvinceCode(entity.getProvinceCode());
        dto.setProvinceDescription(entity.getProvinceDescription());
        dto.setMunicipalityCode(entity.getMunicipalityCode());
        dto.setMunicipalityDescription(entity.getMunicipalityDescription());
        dto.setCadastralReference(entity.getCadastralReference());
        dto.setPropertyTypeCode(entity.getPropertyTypeCode());
        dto.setPropertyTypeDescription(entity.getPropertyTypeDescription());
        dto.setMeasurementUnitCode(entity.getMeasurementUnitCode());
        dto.setSurfaceArea(entity.getSurfaceArea());
        dto.setLocationName(entity.getLocationName());
        dto.setPolygon(entity.getPolygon());
        dto.setParcel(entity.getParcel());
        dto.setDeclaredValue(entity.getDeclaredValue());
        dto.setVerifiedValue(entity.getVerifiedValue());
        dto.setReferenceValue(entity.getReferenceValue());
        dto.setEstimatedValue(entity.getEstimatedValue());
        dto.setConformityIndicator(entity.getConformityIndicator());
        return dto;
    }

    private RuralProperty toEntity(RuralPropertyDTO dto) {
        RuralProperty entity = new RuralProperty();
        entity.setPresentationYear(dto.getPresentationYear());
        entity.setTaxType(dto.getTaxType());
        entity.setPresentationCode(dto.getPresentationCode());
        entity.setTaxpayerNif(dto.getTaxpayerNif());
        entity.setAssetSequence(dto.getAssetSequence());
        entity.setProvinceCode(dto.getProvinceCode());
        entity.setProvinceDescription(dto.getProvinceDescription());
        entity.setMunicipalityCode(dto.getMunicipalityCode());
        entity.setMunicipalityDescription(dto.getMunicipalityDescription());
        entity.setCadastralReference(dto.getCadastralReference());
        entity.setPropertyTypeCode(dto.getPropertyTypeCode());
        entity.setPropertyTypeDescription(dto.getPropertyTypeDescription());
        entity.setMeasurementUnitCode(dto.getMeasurementUnitCode());
        entity.setSurfaceArea(dto.getSurfaceArea());
        entity.setLocationName(dto.getLocationName());
        entity.setPolygon(dto.getPolygon());
        entity.setParcel(dto.getParcel());
        entity.setDeclaredValue(dto.getDeclaredValue());
        entity.setVerifiedValue(dto.getVerifiedValue());
        entity.setReferenceValue(dto.getReferenceValue());
        entity.setEstimatedValue(dto.getEstimatedValue());
        entity.setConformityIndicator(dto.getConformityIndicator());
        return entity;
    }
}