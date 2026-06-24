package com.example.app.service;

import com.example.app.dto.VehicleDTO;
import com.example.app.entity.Vehicle;
import com.example.app.entity.VehicleId;
import com.example.app.entity.VehicleCatalog;
import com.example.app.entity.VehicleModel;
import com.example.app.entity.VehicleModelId;
import com.example.app.repository.VehicleRepository;
import com.example.app.repository.VehicleCatalogRepository;
import com.example.app.repository.VehicleModelRepository;
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
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleCatalogRepository vehicleCatalogRepository;
    private final VehicleModelRepository vehicleModelRepository;

    public VehicleService(VehicleRepository vehicleRepository,
                          VehicleCatalogRepository vehicleCatalogRepository,
                          VehicleModelRepository vehicleModelRepository) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleCatalogRepository = vehicleCatalogRepository;
        this.vehicleModelRepository = vehicleModelRepository;
    }

    public List<VehicleDTO> findAll() {
        return vehicleRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<VehicleDTO> findById(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, Integer assetSequence) {
        VehicleId id = new VehicleId(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
        return vehicleRepository.findById(id).map(this::toDTO);
    }

    public List<VehicleDTO> findByDeclaration(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif) {
        return vehicleRepository.findByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNif(
                presentationYear, taxType, presentationCode, taxpayerNif)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public VehicleDTO save(VehicleDTO dto) {
        determineCatalogDate(dto);
        populateModelData(dto);
        calculateVerifiedValue(dto);

        Vehicle entity = toEntity(dto);
        Vehicle saved = vehicleRepository.save(entity);
        return toDTO(saved);
    }

    public void deleteById(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, Integer assetSequence) {
        VehicleId id = new VehicleId(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
        vehicleRepository.deleteById(id);
    }

    private void determineCatalogDate(VehicleDTO dto) {
        if (dto.getRegistrationDate() != null && dto.getCatalogDate() == null) {
            Optional<VehicleCatalog> catalog = vehicleCatalogRepository.findApplicableCatalog(dto.getRegistrationDate());
            catalog.ifPresent(c -> dto.setCatalogDate(c.getCatalogDate()));
        }
    }

    private void populateModelData(VehicleDTO dto) {
        if (dto.getCatalogDate() != null && dto.getVehicleTypeCode() != null &&
            dto.getVehicleBrandCode() != null && dto.getVehicleModelCode() != null) {
            VehicleModelId modelId = new VehicleModelId(dto.getCatalogDate(), dto.getVehicleTypeCode(),
                    dto.getVehicleBrandCode(), dto.getVehicleModelCode());
            vehicleModelRepository.findById(modelId).ifPresent(model -> {
                dto.setVehicleModelAbbreviation(model.getVehicleModelAbbreviation());
                dto.setEngineDisplacementCc(model.getEngineDisplacementCc());
                dto.setCatalogValue(model.getCatalogValue());
            });
        }
    }

    private void calculateVerifiedValue(VehicleDTO dto) {
        if (dto.getCatalogValue() != null && dto.getRegistrationDate() != null) {
            int vehicleAge = calculateVehicleAge(dto.getRegistrationDate());
            BigDecimal depreciationFactor = getDepreciationFactor(vehicleAge);
            BigDecimal verifiedValue = dto.getCatalogValue().multiply(depreciationFactor)
                    .setScale(2, RoundingMode.HALF_UP);
            dto.setVerifiedValue(verifiedValue);
        }
    }

    private int calculateVehicleAge(Date registrationDate) {
        Calendar regCal = Calendar.getInstance();
        regCal.setTime(registrationDate);
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.YEAR) - regCal.get(Calendar.YEAR);
    }

    private BigDecimal getDepreciationFactor(int age) {
        if (age <= 1) return new BigDecimal("1.00");
        if (age == 2) return new BigDecimal("0.84");
        if (age == 3) return new BigDecimal("0.67");
        if (age == 4) return new BigDecimal("0.56");
        if (age == 5) return new BigDecimal("0.47");
        if (age == 6) return new BigDecimal("0.39");
        if (age == 7) return new BigDecimal("0.34");
        if (age == 8) return new BigDecimal("0.28");
        if (age == 9) return new BigDecimal("0.24");
        if (age == 10) return new BigDecimal("0.19");
        if (age == 11) return new BigDecimal("0.17");
        return new BigDecimal("0.10");
    }

    private VehicleDTO toDTO(Vehicle entity) {
        VehicleDTO dto = new VehicleDTO();
        dto.setPresentationYear(entity.getPresentationYear());
        dto.setTaxType(entity.getTaxType());
        dto.setPresentationCode(entity.getPresentationCode());
        dto.setTaxpayerNif(entity.getTaxpayerNif());
        dto.setAssetSequence(entity.getAssetSequence());
        dto.setRegistrationDate(entity.getRegistrationDate());
        dto.setCatalogDate(entity.getCatalogDate());
        dto.setVehicleTypeCode(entity.getVehicleTypeCode());
        dto.setVehicleTypeDescription(entity.getVehicleTypeDescription());
        dto.setVehicleBrandCode(entity.getVehicleBrandCode());
        dto.setVehicleBrandDescription(entity.getVehicleBrandDescription());
        dto.setVehicleModelCode(entity.getVehicleModelCode());
        dto.setVehicleModelAbbreviation(entity.getVehicleModelAbbreviation());
        dto.setEngineDisplacementCc(entity.getEngineDisplacementCc());
        dto.setCatalogValue(entity.getCatalogValue());
        dto.setDeclaredValue(entity.getDeclaredValue());
        dto.setVerifiedValue(entity.getVerifiedValue());
        dto.setConformityIndicator(entity.getConformityIndicator());
        return dto;
    }

    private Vehicle toEntity(VehicleDTO dto) {
        Vehicle entity = new Vehicle();
        entity.setPresentationYear(dto.getPresentationYear());
        entity.setTaxType(dto.getTaxType());
        entity.setPresentationCode(dto.getPresentationCode());
        entity.setTaxpayerNif(dto.getTaxpayerNif());
        entity.setAssetSequence(dto.getAssetSequence());
        entity.setRegistrationDate(dto.getRegistrationDate());
        entity.setCatalogDate(dto.getCatalogDate());
        entity.setVehicleTypeCode(dto.getVehicleTypeCode());
        entity.setVehicleTypeDescription(dto.getVehicleTypeDescription());
        entity.setVehicleBrandCode(dto.getVehicleBrandCode());
        entity.setVehicleBrandDescription(dto.getVehicleBrandDescription());
        entity.setVehicleModelCode(dto.getVehicleModelCode());
        entity.setVehicleModelAbbreviation(dto.getVehicleModelAbbreviation());
        entity.setEngineDisplacementCc(dto.getEngineDisplacementCc());
        entity.setCatalogValue(dto.getCatalogValue());
        entity.setDeclaredValue(dto.getDeclaredValue());
        entity.setVerifiedValue(dto.getVerifiedValue());
        entity.setConformityIndicator(dto.getConformityIndicator());
        return entity;
    }
}