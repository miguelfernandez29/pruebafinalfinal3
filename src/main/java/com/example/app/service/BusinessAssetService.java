package com.example.app.service;

import com.example.app.dto.BusinessAssetDTO;
import com.example.app.entity.BusinessAsset;
import com.example.app.entity.BusinessAssetId;
import com.example.app.repository.BusinessAssetRepository;
import com.example.app.repository.ProvinceRepository;
import com.example.app.repository.MunicipalityRepository;
import com.example.app.entity.MunicipalityId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BusinessAssetService {

    private final BusinessAssetRepository businessAssetRepository;
    private final ProvinceRepository provinceRepository;
    private final MunicipalityRepository municipalityRepository;

    public BusinessAssetService(BusinessAssetRepository businessAssetRepository,
                                ProvinceRepository provinceRepository,
                                MunicipalityRepository municipalityRepository) {
        this.businessAssetRepository = businessAssetRepository;
        this.provinceRepository = provinceRepository;
        this.municipalityRepository = municipalityRepository;
    }

    public List<BusinessAssetDTO> findAll() {
        return businessAssetRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<BusinessAssetDTO> findById(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, Integer assetSequence) {
        BusinessAssetId id = new BusinessAssetId(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
        return businessAssetRepository.findById(id).map(this::toDTO);
    }

    public List<BusinessAssetDTO> findByDeclaration(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif) {
        return businessAssetRepository.findByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNif(
                presentationYear, taxType, presentationCode, taxpayerNif)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public BusinessAssetDTO save(BusinessAssetDTO dto) {
        validateIndicators(dto);
        validateDeclaredValue(dto.getDeclaredValue());
        populateDescriptions(dto);

        BusinessAsset entity = toEntity(dto);
        BusinessAsset saved = businessAssetRepository.save(entity);
        return toDTO(saved);
    }

    public void deleteById(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, Integer assetSequence) {
        BusinessAssetId id = new BusinessAssetId(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
        businessAssetRepository.deleteById(id);
    }

    private void validateIndicators(BusinessAssetDTO dto) {
        if (dto.getReductionIndicator() != null && !dto.getReductionIndicator().matches("[SN]")) {
            throw new IllegalArgumentException("Valores permitidos para reduccion: S o N.");
        }
        if (dto.getAffectationIndicator() != null && !dto.getAffectationIndicator().matches("[SN]")) {
            throw new IllegalArgumentException("Valores permitidos para afectacion: S o N.");
        }
    }

    private void validateDeclaredValue(BigDecimal declaredValue) {
        if (declaredValue != null && declaredValue.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El valor declarado debe ser positivo.");
        }
    }

    private void populateDescriptions(BusinessAssetDTO dto) {
        if (dto.getProvinceCode() != null) {
            provinceRepository.findById(dto.getProvinceCode())
                    .ifPresent(p -> dto.setProvinceDescription(p.getProvinceDescription()));
        }
        if (dto.getProvinceCode() != null && dto.getMunicipalityCode() != null) {
            MunicipalityId mId = new MunicipalityId(dto.getProvinceCode(), dto.getMunicipalityCode());
            municipalityRepository.findById(mId)
                    .ifPresent(m -> dto.setMunicipalityDescription(m.getMunicipalityDescription()));
        }
    }

    private BusinessAssetDTO toDTO(BusinessAsset entity) {
        BusinessAssetDTO dto = new BusinessAssetDTO();
        dto.setPresentationYear(entity.getPresentationYear());
        dto.setTaxType(entity.getTaxType());
        dto.setPresentationCode(entity.getPresentationCode());
        dto.setTaxpayerNif(entity.getTaxpayerNif());
        dto.setAssetSequence(entity.getAssetSequence());
        dto.setActivityCode(entity.getActivityCode());
        dto.setActivityDescription(entity.getActivityDescription());
        dto.setEpigraphCode(entity.getEpigraphCode());
        dto.setEpigraphDescription(entity.getEpigraphDescription());
        dto.setAssetDescription(entity.getAssetDescription());
        dto.setProvinceCode(entity.getProvinceCode());
        dto.setProvinceDescription(entity.getProvinceDescription());
        dto.setMunicipalityCode(entity.getMunicipalityCode());
        dto.setMunicipalityDescription(entity.getMunicipalityDescription());
        dto.setPostalCode(entity.getPostalCode());
        dto.setStreetTypeCode(entity.getStreetTypeCode());
        dto.setStreetName(entity.getStreetName());
        dto.setStreetNumber(entity.getStreetNumber());
        dto.setReductionIndicator(entity.getReductionIndicator());
        dto.setAffectationIndicator(entity.getAffectationIndicator());
        dto.setDeclaredValue(entity.getDeclaredValue());
        dto.setVerifiedValue(entity.getVerifiedValue());
        dto.setConformityIndicator(entity.getConformityIndicator());
        return dto;
    }

    private BusinessAsset toEntity(BusinessAssetDTO dto) {
        BusinessAsset entity = new BusinessAsset();
        entity.setPresentationYear(dto.getPresentationYear());
        entity.setTaxType(dto.getTaxType());
        entity.setPresentationCode(dto.getPresentationCode());
        entity.setTaxpayerNif(dto.getTaxpayerNif());
        entity.setAssetSequence(dto.getAssetSequence());
        entity.setActivityCode(dto.getActivityCode());
        entity.setActivityDescription(dto.getActivityDescription());
        entity.setEpigraphCode(dto.getEpigraphCode());
        entity.setEpigraphDescription(dto.getEpigraphDescription());
        entity.setAssetDescription(dto.getAssetDescription());
        entity.setProvinceCode(dto.getProvinceCode());
        entity.setProvinceDescription(dto.getProvinceDescription());
        entity.setMunicipalityCode(dto.getMunicipalityCode());
        entity.setMunicipalityDescription(dto.getMunicipalityDescription());
        entity.setPostalCode(dto.getPostalCode());
        entity.setStreetTypeCode(dto.getStreetTypeCode());
        entity.setStreetName(dto.getStreetName());
        entity.setStreetNumber(dto.getStreetNumber());
        entity.setReductionIndicator(dto.getReductionIndicator());
        entity.setAffectationIndicator(dto.getAffectationIndicator());
        entity.setDeclaredValue(dto.getDeclaredValue());
        entity.setVerifiedValue(dto.getVerifiedValue());
        entity.setConformityIndicator(dto.getConformityIndicator());
        return entity;
    }
}