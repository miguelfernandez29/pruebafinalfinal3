package com.example.app.service;

import com.example.app.dto.OtherAssetDTO;
import com.example.app.entity.OtherAsset;
import com.example.app.entity.OtherAssetId;
import com.example.app.repository.OtherAssetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OtherAssetService {

    private final OtherAssetRepository otherAssetRepository;

    public OtherAssetService(OtherAssetRepository otherAssetRepository) {
        this.otherAssetRepository = otherAssetRepository;
    }

    public List<OtherAssetDTO> findAll() {
        return otherAssetRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<OtherAssetDTO> findById(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, Integer assetSequence) {
        OtherAssetId id = new OtherAssetId(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
        return otherAssetRepository.findById(id).map(this::toDTO);
    }

    public List<OtherAssetDTO> findByDeclaration(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif) {
        return otherAssetRepository.findByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNif(
                presentationYear, taxType, presentationCode, taxpayerNif)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public OtherAssetDTO save(OtherAssetDTO dto) {
        validateNumberOfUnits(dto.getNumberOfUnits());
        validateDeclaredValue(dto.getDeclaredValue());

        OtherAsset entity = toEntity(dto);
        OtherAsset saved = otherAssetRepository.save(entity);
        return toDTO(saved);
    }

    public void deleteById(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, Integer assetSequence) {
        OtherAssetId id = new OtherAssetId(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
        otherAssetRepository.deleteById(id);
    }

    private void validateNumberOfUnits(BigDecimal numberOfUnits) {
        if (numberOfUnits != null && numberOfUnits.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El numero de unidades debe ser positivo.");
        }
    }

    private void validateDeclaredValue(BigDecimal declaredValue) {
        if (declaredValue != null && declaredValue.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El valor declarado debe ser positivo.");
        }
    }

    private OtherAssetDTO toDTO(OtherAsset entity) {
        OtherAssetDTO dto = new OtherAssetDTO();
        dto.setPresentationYear(entity.getPresentationYear());
        dto.setTaxType(entity.getTaxType());
        dto.setPresentationCode(entity.getPresentationCode());
        dto.setTaxpayerNif(entity.getTaxpayerNif());
        dto.setAssetSequence(entity.getAssetSequence());
        dto.setAssetDescription(entity.getAssetDescription());
        dto.setNumberOfUnits(entity.getNumberOfUnits());
        dto.setDeclaredValue(entity.getDeclaredValue());
        dto.setVerifiedValue(entity.getVerifiedValue());
        dto.setConformityIndicator(entity.getConformityIndicator());
        return dto;
    }

    private OtherAsset toEntity(OtherAssetDTO dto) {
        OtherAsset entity = new OtherAsset();
        entity.setPresentationYear(dto.getPresentationYear());
        entity.setTaxType(dto.getTaxType());
        entity.setPresentationCode(dto.getPresentationCode());
        entity.setTaxpayerNif(dto.getTaxpayerNif());
        entity.setAssetSequence(dto.getAssetSequence());
        entity.setAssetDescription(dto.getAssetDescription());
        entity.setNumberOfUnits(dto.getNumberOfUnits());
        entity.setDeclaredValue(dto.getDeclaredValue());
        entity.setVerifiedValue(dto.getVerifiedValue());
        entity.setConformityIndicator(dto.getConformityIndicator());
        return entity;
    }
}