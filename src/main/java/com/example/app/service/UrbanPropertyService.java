package com.example.app.service;

import com.example.app.dto.UrbanPropertyDTO;
import com.example.app.entity.UrbanProperty;
import com.example.app.entity.UrbanPropertyId;
import com.example.app.repository.UrbanPropertyRepository;
import com.example.app.repository.ProvinceRepository;
import com.example.app.repository.MunicipalityRepository;
import com.example.app.repository.PropertyTypeRepository;
import com.example.app.entity.Province;
import com.example.app.entity.Municipality;
import com.example.app.entity.MunicipalityId;
import com.example.app.entity.PropertyType;
import com.example.app.entity.PropertyTypeId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UrbanPropertyService {

    private final UrbanPropertyRepository urbanPropertyRepository;
    private final ProvinceRepository provinceRepository;
    private final MunicipalityRepository municipalityRepository;
    private final PropertyTypeRepository propertyTypeRepository;

    public UrbanPropertyService(UrbanPropertyRepository urbanPropertyRepository,
                                ProvinceRepository provinceRepository,
                                MunicipalityRepository municipalityRepository,
                                PropertyTypeRepository propertyTypeRepository) {
        this.urbanPropertyRepository = urbanPropertyRepository;
        this.provinceRepository = provinceRepository;
        this.municipalityRepository = municipalityRepository;
        this.propertyTypeRepository = propertyTypeRepository;
    }

    public List<UrbanPropertyDTO> findAll() {
        return urbanPropertyRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<UrbanPropertyDTO> findById(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, Integer assetSequence) {
        UrbanPropertyId id = new UrbanPropertyId(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
        return urbanPropertyRepository.findById(id).map(this::toDTO);
    }

    public List<UrbanPropertyDTO> findByDeclaration(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif) {
        return urbanPropertyRepository.findByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNif(
                presentationYear, taxType, presentationCode, taxpayerNif)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public UrbanPropertyDTO save(UrbanPropertyDTO dto) {
        validateProvinceCode(dto.getProvinceCode());
        validateMunicipalityCode(dto.getProvinceCode(), dto.getMunicipalityCode());
        validatePropertyTypeCode(dto.getPropertyTypeCode());
        validateCadastralReference(dto.getCadastralReference());
        validateConstructionYear(dto.getConstructionYear());
        validateRentalContractYear(dto.getRentalContractYear(), dto.getRentalIndicator());
        validateHabitualResidenceValue(dto.getHabitualResidenceValue(), dto.getHabitualResidenceIndicator(), dto.getVerifiedValue());
        validateDeclaredValue(dto.getDeclaredValue());
        validateIndicators(dto);

        populateDescriptions(dto);
        calculateConformity(dto);

        UrbanProperty entity = toEntity(dto);
        UrbanProperty saved = urbanPropertyRepository.save(entity);
        return toDTO(saved);
    }

    public void deleteById(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, Integer assetSequence) {
        UrbanPropertyId id = new UrbanPropertyId(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
        urbanPropertyRepository.deleteById(id);
    }

    private void validateProvinceCode(String provinceCode) {
        if (provinceCode == null) {
            throw new IllegalArgumentException("El campo \"Provincia\" es obligatorio.");
        }
        if (!provinceRepository.existsById(provinceCode)) {
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
            PropertyTypeId id = new PropertyTypeId("U", propertyTypeCode);
            if (!propertyTypeRepository.existsById(id)) {
                throw new IllegalArgumentException("Tipo de bien no encontrado.");
            }
        }
    }

    private void validateCadastralReference(String cadastralReference) {
        if (cadastralReference != null) {
            String cleaned = cadastralReference.trim().replace(" ", "");
            if (cleaned.length() != 20 && cleaned.length() != 14) {
                throw new IllegalArgumentException("Referencia catastral no valida.");
            }
        }
    }

    private void validateConstructionYear(Integer constructionYear) {
        if (constructionYear != null) {
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            if (constructionYear < 1500 || constructionYear > currentYear) {
                throw new IllegalArgumentException("El ano de construccion debe de estar entre 1500 y el ano actual.");
            }
        }
    }

    private void validateRentalContractYear(Integer rentalContractYear, String rentalIndicator) {
        if (rentalContractYear != null) {
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            if (rentalContractYear < 1500 || rentalContractYear > currentYear) {
                throw new IllegalArgumentException("El ano de contrato debe de estar entre 1500 y el ano actual.");
            }
            if (!"S".equals(rentalIndicator)) {
                throw new IllegalArgumentException("El ano de contrato requiere indicador de arrendamiento.");
            }
        }
    }

    private void validateHabitualResidenceValue(BigDecimal habitualResidenceValue, String habitualResidenceIndicator, BigDecimal verifiedValue) {
        if (habitualResidenceValue != null && verifiedValue != null) {
            if (habitualResidenceValue.compareTo(verifiedValue) > 0) {
                throw new IllegalArgumentException("El valor de vivienda habitual no puede exceder el valor comprobado.");
            }
        }
    }

    private void validateDeclaredValue(BigDecimal declaredValue) {
        if (declaredValue != null && declaredValue.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El valor declarado debe ser positivo.");
        }
    }

    private void validateIndicators(UrbanPropertyDTO dto) {
        if (dto.getRentalIndicator() != null && !dto.getRentalIndicator().matches("[SN]")) {
            throw new IllegalArgumentException("Valores permitidos para arrendamiento: S o N.");
        }
        if (dto.getOfficialProtectionIndicator() != null && !dto.getOfficialProtectionIndicator().matches("[SN]")) {
            throw new IllegalArgumentException("Valores permitidos para proteccion oficial: S o N.");
        }
        if (dto.getDisqualificationIndicator() != null && !dto.getDisqualificationIndicator().matches("[SN]")) {
            throw new IllegalArgumentException("Valores permitidos para descalificacion: S o N.");
        }
        if (dto.getHabitualResidenceIndicator() != null && !dto.getHabitualResidenceIndicator().matches("[SN]")) {
            throw new IllegalArgumentException("Valores permitidos para vivienda habitual: S o N.");
        }
    }

    private void populateDescriptions(UrbanPropertyDTO dto) {
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
            PropertyTypeId ptId = new PropertyTypeId("U", dto.getPropertyTypeCode());
            propertyTypeRepository.findById(ptId)
                    .ifPresent(pt -> dto.setPropertyTypeDescription(pt.getPropertyTypeDescription()));
        }
        if (dto.getCountryCode() == null) {
            dto.setCountryCode("ESP");
        }
    }

    private void calculateConformity(UrbanPropertyDTO dto) {
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

    private UrbanPropertyDTO toDTO(UrbanProperty entity) {
        UrbanPropertyDTO dto = new UrbanPropertyDTO();
        dto.setPresentationYear(entity.getPresentationYear());
        dto.setTaxType(entity.getTaxType());
        dto.setPresentationCode(entity.getPresentationCode());
        dto.setTaxpayerNif(entity.getTaxpayerNif());
        dto.setAssetSequence(entity.getAssetSequence());
        dto.setProvinceCode(entity.getProvinceCode());
        dto.setProvinceDescription(entity.getProvinceDescription());
        dto.setMunicipalityCode(entity.getMunicipalityCode());
        dto.setMunicipalityDescription(entity.getMunicipalityDescription());
        dto.setPostalCode(entity.getPostalCode());
        dto.setStreetTypeCode(entity.getStreetTypeCode());
        dto.setStreetName(entity.getStreetName());
        dto.setStreetNumber(entity.getStreetNumber());
        dto.setStairway(entity.getStairway());
        dto.setFloor(entity.getFloor());
        dto.setDoor(entity.getDoor());
        dto.setCadastralReference(entity.getCadastralReference());
        dto.setPropertyTypeCode(entity.getPropertyTypeCode());
        dto.setPropertyTypeDescription(entity.getPropertyTypeDescription());
        dto.setSurfaceArea(entity.getSurfaceArea());
        dto.setConstructionYear(entity.getConstructionYear());
        dto.setRentalIndicator(entity.getRentalIndicator());
        dto.setRentalContractYear(entity.getRentalContractYear());
        dto.setOfficialProtectionIndicator(entity.getOfficialProtectionIndicator());
        dto.setMaxSalePrice(entity.getMaxSalePrice());
        dto.setDisqualificationIndicator(entity.getDisqualificationIndicator());
        dto.setHabitualResidenceIndicator(entity.getHabitualResidenceIndicator());
        dto.setHabitualResidenceValue(entity.getHabitualResidenceValue());
        dto.setDeclaredValue(entity.getDeclaredValue());
        dto.setVerifiedValue(entity.getVerifiedValue());
        dto.setReferenceValue(entity.getReferenceValue());
        dto.setEstimatedValue(entity.getEstimatedValue());
        dto.setConformityIndicator(entity.getConformityIndicator());
        dto.setUrbanZoneCode(entity.getUrbanZoneCode());
        dto.setSectorCode(entity.getSectorCode());
        dto.setCountryCode(entity.getCountryCode());
        return dto;
    }

    private UrbanProperty toEntity(UrbanPropertyDTO dto) {
        UrbanProperty entity = new UrbanProperty();
        entity.setPresentationYear(dto.getPresentationYear());
        entity.setTaxType(dto.getTaxType());
        entity.setPresentationCode(dto.getPresentationCode());
        entity.setTaxpayerNif(dto.getTaxpayerNif());
        entity.setAssetSequence(dto.getAssetSequence());
        entity.setProvinceCode(dto.getProvinceCode());
        entity.setProvinceDescription(dto.getProvinceDescription());
        entity.setMunicipalityCode(dto.getMunicipalityCode());
        entity.setMunicipalityDescription(dto.getMunicipalityDescription());
        entity.setPostalCode(dto.getPostalCode());
        entity.setStreetTypeCode(dto.getStreetTypeCode());
        entity.setStreetName(dto.getStreetName());
        entity.setStreetNumber(dto.getStreetNumber());
        entity.setStairway(dto.getStairway());
        entity.setFloor(dto.getFloor());
        entity.setDoor(dto.getDoor());
        entity.setCadastralReference(dto.getCadastralReference());
        entity.setPropertyTypeCode(dto.getPropertyTypeCode());
        entity.setPropertyTypeDescription(dto.getPropertyTypeDescription());
        entity.setSurfaceArea(dto.getSurfaceArea());
        entity.setConstructionYear(dto.getConstructionYear());
        entity.setRentalIndicator(dto.getRentalIndicator());
        entity.setRentalContractYear(dto.getRentalContractYear());
        entity.setOfficialProtectionIndicator(dto.getOfficialProtectionIndicator());
        entity.setMaxSalePrice(dto.getMaxSalePrice());
        entity.setDisqualificationIndicator(dto.getDisqualificationIndicator());
        entity.setHabitualResidenceIndicator(dto.getHabitualResidenceIndicator());
        entity.setHabitualResidenceValue(dto.getHabitualResidenceValue());
        entity.setDeclaredValue(dto.getDeclaredValue());
        entity.setVerifiedValue(dto.getVerifiedValue());
        entity.setReferenceValue(dto.getReferenceValue());
        entity.setEstimatedValue(dto.getEstimatedValue());
        entity.setConformityIndicator(dto.getConformityIndicator());
        entity.setUrbanZoneCode(dto.getUrbanZoneCode());
        entity.setSectorCode(dto.getSectorCode());
        entity.setCountryCode(dto.getCountryCode());
        return entity;
    }
}