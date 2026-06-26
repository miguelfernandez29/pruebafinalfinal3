package com.example.app.service;

import com.example.app.dto.OtherAssetDto;
import com.example.app.entity.OtherAsset;
import com.example.app.entity.OtherAssetId;
import com.example.app.exception.BusinessValidationException;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.repository.OtherAssetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OtherAssetService {

    private final OtherAssetRepository otherAssetRepository;

    public OtherAssetService(OtherAssetRepository otherAssetRepository) {
        this.otherAssetRepository = otherAssetRepository;
    }

    public List<OtherAssetDto> findAll() {
        return otherAssetRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public OtherAssetDto findById(Integer presentationYear, String taxTypeCode, String presentationCode,
                                   String causeNif, String subCauseCode, Integer assetSequence) {
        OtherAssetId id = new OtherAssetId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        OtherAsset entity = otherAssetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OtherAsset", "id", id));
        return toDto(entity);
    }

    public OtherAssetDto create(OtherAssetDto dto) {
        validateOtherAsset(dto);
        OtherAsset entity = toEntity(dto);
        OtherAsset saved = otherAssetRepository.save(entity);
        return toDto(saved);
    }

    public OtherAssetDto update(Integer presentationYear, String taxTypeCode, String presentationCode,
                                 String causeNif, String subCauseCode, Integer assetSequence,
                                 OtherAssetDto dto) {
        OtherAssetId id = new OtherAssetId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        OtherAsset existing = otherAssetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OtherAsset", "id", id));
        
        validateOtherAsset(dto);
        updateEntityFromDto(existing, dto);
        
        OtherAsset saved = otherAssetRepository.save(existing);
        return toDto(saved);
    }

    public void delete(Integer presentationYear, String taxTypeCode, String presentationCode,
                       String causeNif, String subCauseCode, Integer assetSequence) {
        OtherAssetId id = new OtherAssetId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        OtherAsset existing = otherAssetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OtherAsset", "id", id));
        otherAssetRepository.delete(existing);
    }

    private void validateOtherAsset(OtherAssetDto dto) {
        if (dto.getDeclaredValue() != null && dto.getDeclaredValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessValidationException("INVALID_VALUE", "declaredValue",
                    "Declared value must be positive");
        }
        
        if (dto.getVerifiedValue() != null && dto.getVerifiedValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessValidationException("INVALID_VALUE", "verifiedValue",
                    "Verified value must be positive");
        }
    }

    private OtherAssetDto toDto(OtherAsset entity) {
        OtherAssetDto dto = new OtherAssetDto();
        dto.setPresentationYear(entity.getPresentationYear());
        dto.setTaxTypeCode(entity.getTaxTypeCode());
        dto.setPresentationCode(entity.getPresentationCode());
        dto.setCauseNif(entity.getCauseNif());
        dto.setSubCauseCode(entity.getSubCauseCode());
        dto.setAssetSequence(entity.getAssetSequence());
        dto.setAssetTypeCode(entity.getAssetTypeCode());
        dto.setAssetTypeDescription(entity.getAssetTypeDescription());
        dto.setDescription(entity.getDescription());
        dto.setDeclaredValue(entity.getDeclaredValue());
        dto.setVerifiedValue(entity.getVerifiedValue());
        return dto;
    }

    private OtherAsset toEntity(OtherAssetDto dto) {
        OtherAsset entity = new OtherAsset();
        entity.setPresentationYear(dto.getPresentationYear());
        entity.setTaxTypeCode(dto.getTaxTypeCode());
        entity.setPresentationCode(dto.getPresentationCode());
        entity.setCauseNif(dto.getCauseNif());
        entity.setSubCauseCode(dto.getSubCauseCode());
        entity.setAssetSequence(dto.getAssetSequence());
        updateEntityFromDto(entity, dto);
        return entity;
    }

    private void updateEntityFromDto(OtherAsset entity, OtherAssetDto dto) {
        entity.setAssetTypeCode(dto.getAssetTypeCode());
        entity.setAssetTypeDescription(dto.getAssetTypeDescription());
        entity.setDescription(dto.getDescription());
        entity.setDeclaredValue(dto.getDeclaredValue());
        entity.setVerifiedValue(dto.getVerifiedValue());
    }
}