package com.example.app.service;

import com.example.app.dto.VehicleAssetDto;
import com.example.app.entity.VehicleAsset;
import com.example.app.entity.VehicleAssetId;
import com.example.app.exception.BusinessValidationException;
import com.example.app.exception.DuplicateAssetException;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.repository.VehicleAssetRepository;
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
public class VehicleAssetService {

    private final VehicleAssetRepository vehicleAssetRepository;

    public VehicleAssetService(VehicleAssetRepository vehicleAssetRepository) {
        this.vehicleAssetRepository = vehicleAssetRepository;
    }

    public List<VehicleAssetDto> findAll() {
        return vehicleAssetRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public VehicleAssetDto findById(Integer presentationYear, String taxTypeCode, String presentationCode,
                                     String causeNif, String subCauseCode, Integer assetSequence) {
        VehicleAssetId id = new VehicleAssetId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        VehicleAsset entity = vehicleAssetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VehicleAsset", "id", id));
        return toDto(entity);
    }

    public VehicleAssetDto create(VehicleAssetDto dto) {
        validateVehicleAsset(dto);
        
        if (dto.getLicensePlate() != null) {
            Optional<VehicleAsset> existing = vehicleAssetRepository.findByLicensePlate(dto.getLicensePlate());
            if (existing.isPresent()) {
                throw new DuplicateAssetException("VehicleAsset", dto.getLicensePlate());
            }
        }
        
        VehicleAsset entity = toEntity(dto);
        calculateVerifiedValue(entity);
        VehicleAsset saved = vehicleAssetRepository.save(entity);
        return toDto(saved);
    }

    public VehicleAssetDto update(Integer presentationYear, String taxTypeCode, String presentationCode,
                                   String causeNif, String subCauseCode, Integer assetSequence,
                                   VehicleAssetDto dto) {
        VehicleAssetId id = new VehicleAssetId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        VehicleAsset existing = vehicleAssetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VehicleAsset", "id", id));
        
        validateVehicleAsset(dto);
        updateEntityFromDto(existing, dto);
        calculateVerifiedValue(existing);
        
        VehicleAsset saved = vehicleAssetRepository.save(existing);
        return toDto(saved);
    }

    public void delete(Integer presentationYear, String taxTypeCode, String presentationCode,
                       String causeNif, String subCauseCode, Integer assetSequence) {
        VehicleAssetId id = new VehicleAssetId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        VehicleAsset existing = vehicleAssetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VehicleAsset", "id", id));
        vehicleAssetRepository.delete(existing);
    }

    public VehicleAssetDto findByLicensePlate(String licensePlate) {
        VehicleAsset entity = vehicleAssetRepository.findByLicensePlate(licensePlate)
                .orElseThrow(() -> new ResourceNotFoundException("VehicleAsset", "licensePlate", licensePlate));
        return toDto(entity);
    }

    public List<VehicleAssetDto> findByTypeAndBrand(String vehicleTypeCode, String vehicleBrandCode) {
        return vehicleAssetRepository.findByTypeAndBrand(vehicleTypeCode, vehicleBrandCode).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private void validateVehicleAsset(VehicleAssetDto dto) {
        if (dto.getLicensePlate() != null && dto.getLicensePlate().length() > 10) {
            throw new BusinessValidationException("INVALID_LICENSE_PLATE", "licensePlate",
                    "License plate must be 10 characters or less");
        }
        
        if (dto.getRegistrationDate() != null) {
            Date now = new Date();
            if (dto.getRegistrationDate().after(now)) {
                throw new BusinessValidationException("INVALID_DATE", "registrationDate",
                        "Registration date cannot be in the future");
            }
        }
        
        if (dto.getCatalogValue() != null && dto.getCatalogValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessValidationException("INVALID_VALUE", "catalogValue",
                    "Catalog value must be positive");
        }
        
        if (dto.getDepreciationPercentage() != null) {
            if (dto.getDepreciationPercentage().compareTo(BigDecimal.ZERO) < 0 ||
                dto.getDepreciationPercentage().compareTo(new BigDecimal("100")) > 0) {
                throw new BusinessValidationException("INVALID_PERCENTAGE", "depreciationPercentage",
                        "Depreciation percentage must be between 0 and 100");
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

    private void calculateVerifiedValue(VehicleAsset entity) {
        if (entity.getCatalogValue() != null && entity.getDepreciationPercentage() != null) {
            BigDecimal depreciationFactor = BigDecimal.ONE.subtract(
                    entity.getDepreciationPercentage().divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP));
            BigDecimal verifiedValue = entity.getCatalogValue().multiply(depreciationFactor);
            entity.setVerifiedValue(verifiedValue.setScale(2, RoundingMode.HALF_UP));
        } else if (entity.getCatalogValue() != null && entity.getRegistrationDate() != null) {
            int yearsOld = calculateYearsOld(entity.getRegistrationDate());
            BigDecimal depreciationPercentage = calculateDepreciation(yearsOld);
            entity.setDepreciationPercentage(depreciationPercentage);
            
            BigDecimal depreciationFactor = BigDecimal.ONE.subtract(
                    depreciationPercentage.divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP));
            BigDecimal verifiedValue = entity.getCatalogValue().multiply(depreciationFactor);
            entity.setVerifiedValue(verifiedValue.setScale(2, RoundingMode.HALF_UP));
        }
    }

    private int calculateYearsOld(Date registrationDate) {
        Calendar regCal = Calendar.getInstance();
        regCal.setTime(registrationDate);
        Calendar nowCal = Calendar.getInstance();
        return nowCal.get(Calendar.YEAR) - regCal.get(Calendar.YEAR);
    }

    private BigDecimal calculateDepreciation(int yearsOld) {
        if (yearsOld <= 1) return new BigDecimal("0");
        if (yearsOld == 2) return new BigDecimal("16");
        if (yearsOld == 3) return new BigDecimal("28");
        if (yearsOld == 4) return new BigDecimal("37");
        if (yearsOld == 5) return new BigDecimal("45");
        if (yearsOld == 6) return new BigDecimal("52");
        if (yearsOld == 7) return new BigDecimal("58");
        if (yearsOld == 8) return new BigDecimal("63");
        if (yearsOld == 9) return new BigDecimal("68");
        if (yearsOld == 10) return new BigDecimal("72");
        if (yearsOld == 11) return new BigDecimal("76");
        return new BigDecimal("80");
    }

    private VehicleAssetDto toDto(VehicleAsset entity) {
        VehicleAssetDto dto = new VehicleAssetDto();
        dto.setPresentationYear(entity.getPresentationYear());
        dto.setTaxTypeCode(entity.getTaxTypeCode());
        dto.setPresentationCode(entity.getPresentationCode());
        dto.setCauseNif(entity.getCauseNif());
        dto.setSubCauseCode(entity.getSubCauseCode());
        dto.setAssetSequence(entity.getAssetSequence());
        dto.setAssetTypeCode(entity.getAssetTypeCode());
        dto.setAssetTypeDescription(entity.getAssetTypeDescription());
        dto.setVehicleTypeCode(entity.getVehicleTypeCode());
        dto.setVehicleTypeDescription(entity.getVehicleTypeDescription());
        dto.setVehicleBrandCode(entity.getVehicleBrandCode());
        dto.setVehicleBrandDescription(entity.getVehicleBrandDescription());
        dto.setVehicleModelCode(entity.getVehicleModelCode());
        dto.setVehicleModelAbbreviation(entity.getVehicleModelAbbreviation());
        dto.setVehicleModelDescription(entity.getVehicleModelDescription());
        dto.setLicensePlate(entity.getLicensePlate());
        dto.setRegistrationDate(entity.getRegistrationDate());
        dto.setCatalogDate(entity.getCatalogDate());
        dto.setCatalogValue(entity.getCatalogValue());
        dto.setDepreciationPercentage(entity.getDepreciationPercentage());
        dto.setDeclaredValue(entity.getDeclaredValue());
        dto.setVerifiedValue(entity.getVerifiedValue());
        dto.setParticipationPercentage(entity.getParticipationPercentage());
        return dto;
    }

    private VehicleAsset toEntity(VehicleAssetDto dto) {
        VehicleAsset entity = new VehicleAsset();
        entity.setPresentationYear(dto.getPresentationYear());
        entity.setTaxTypeCode(dto.getTaxTypeCode());
        entity.setPresentationCode(dto.getPresentationCode());
        entity.setCauseNif(dto.getCauseNif());
        entity.setSubCauseCode(dto.getSubCauseCode());
        entity.setAssetSequence(dto.getAssetSequence());
        updateEntityFromDto(entity, dto);
        return entity;
    }

    private void updateEntityFromDto(VehicleAsset entity, VehicleAssetDto dto) {
        entity.setAssetTypeCode(dto.getAssetTypeCode());
        entity.setAssetTypeDescription(dto.getAssetTypeDescription());
        entity.setVehicleTypeCode(dto.getVehicleTypeCode());
        entity.setVehicleTypeDescription(dto.getVehicleTypeDescription());
        entity.setVehicleBrandCode(dto.getVehicleBrandCode());
        entity.setVehicleBrandDescription(dto.getVehicleBrandDescription());
        entity.setVehicleModelCode(dto.getVehicleModelCode());
        entity.setVehicleModelAbbreviation(dto.getVehicleModelAbbreviation());
        entity.setVehicleModelDescription(dto.getVehicleModelDescription());
        entity.setLicensePlate(dto.getLicensePlate());
        entity.setRegistrationDate(dto.getRegistrationDate());
        entity.setCatalogDate(dto.getCatalogDate());
        entity.setCatalogValue(dto.getCatalogValue());
        entity.setDepreciationPercentage(dto.getDepreciationPercentage());
        entity.setDeclaredValue(dto.getDeclaredValue());
        entity.setVerifiedValue(dto.getVerifiedValue());
        entity.setParticipationPercentage(dto.getParticipationPercentage());
    }
}