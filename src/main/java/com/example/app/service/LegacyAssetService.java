package com.example.app.service;

import com.example.app.dto.LegacyAssetDTO;
import com.example.app.entity.LegacyAsset;
import com.example.app.entity.LegacyAssetId;
import com.example.app.entity.SuccessorCause;
import com.example.app.entity.SuccessorCauseId;
import com.example.app.repository.LegacyAssetRepository;
import com.example.app.repository.SuccessorCauseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class LegacyAssetService {

    private final LegacyAssetRepository legacyAssetRepository;
    private final SuccessorCauseRepository successorCauseRepository;

    public LegacyAssetService(LegacyAssetRepository legacyAssetRepository,
                              SuccessorCauseRepository successorCauseRepository) {
        this.legacyAssetRepository = legacyAssetRepository;
        this.successorCauseRepository = successorCauseRepository;
    }

    public List<LegacyAssetDTO> findAll() {
        return legacyAssetRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<LegacyAssetDTO> findById(Integer presentationYear, String taxType, String presentationCode,
                                              String taxpayerNif, Integer assetSequence, String heirNif, String heirSubsequence) {
        LegacyAssetId id = new LegacyAssetId(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence, heirNif, heirSubsequence);
        return legacyAssetRepository.findById(id).map(this::toDTO);
    }

    public List<LegacyAssetDTO> findByAsset(Integer presentationYear, String taxType, String presentationCode,
                                             String taxpayerNif, Integer assetSequence) {
        return legacyAssetRepository.findByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNifAndAssetSequence(
                presentationYear, taxType, presentationCode, taxpayerNif, assetSequence)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public LegacyAssetDTO save(LegacyAssetDTO dto) {
        validateHeirExists(dto);
        validateLegacyPercentage(dto.getLegacyPercentage());

        LegacyAsset entity = toEntity(dto);
        LegacyAsset saved = legacyAssetRepository.save(entity);
        return toDTO(saved);
    }

    public void deleteById(Integer presentationYear, String taxType, String presentationCode,
                           String taxpayerNif, Integer assetSequence, String heirNif, String heirSubsequence) {
        LegacyAssetId id = new LegacyAssetId(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence, heirNif, heirSubsequence);
        legacyAssetRepository.deleteById(id);
    }

    public void deleteByAsset(Integer presentationYear, String taxType, String presentationCode,
                              String taxpayerNif, Integer assetSequence) {
        legacyAssetRepository.deleteByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNifAndAssetSequence(
                presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
    }

    private void validateHeirExists(LegacyAssetDTO dto) {
        SuccessorCauseId successorId = new SuccessorCauseId(dto.getPresentationYear(), dto.getTaxType(),
                dto.getPresentationCode(), dto.getTaxpayerNif(), dto.getHeirNif(), dto.getHeirSubsequence());
        if (!successorCauseRepository.existsById(successorId)) {
            throw new IllegalArgumentException("El heredero no existe en la declaracion.");
        }
    }

    private void validateLegacyPercentage(BigDecimal legacyPercentage) {
        if (legacyPercentage != null) {
            if (legacyPercentage.compareTo(BigDecimal.ZERO) < 0 || legacyPercentage.compareTo(new BigDecimal("100")) > 0) {
                throw new IllegalArgumentException("El porcentaje de legado debe estar entre 0 y 100.");
            }
        }
    }

    private LegacyAssetDTO toDTO(LegacyAsset entity) {
        LegacyAssetDTO dto = new LegacyAssetDTO();
        dto.setPresentationYear(entity.getPresentationYear());
        dto.setTaxType(entity.getTaxType());
        dto.setPresentationCode(entity.getPresentationCode());
        dto.setTaxpayerNif(entity.getTaxpayerNif());
        dto.setAssetSequence(entity.getAssetSequence());
        dto.setHeirNif(entity.getHeirNif());
        dto.setHeirSubsequence(entity.getHeirSubsequence());
        dto.setLegacyIndicator(entity.getLegacyIndicator());
        dto.setLegacyPercentage(entity.getLegacyPercentage());
        dto.setAcquisitionTypeCode(entity.getAcquisitionTypeCode());
        dto.setHeirName(entity.getHeirName());
        return dto;
    }

    private LegacyAsset toEntity(LegacyAssetDTO dto) {
        LegacyAsset entity = new LegacyAsset();
        entity.setPresentationYear(dto.getPresentationYear());
        entity.setTaxType(dto.getTaxType());
        entity.setPresentationCode(dto.getPresentationCode());
        entity.setTaxpayerNif(dto.getTaxpayerNif());
        entity.setAssetSequence(dto.getAssetSequence());
        entity.setHeirNif(dto.getHeirNif());
        entity.setHeirSubsequence(dto.getHeirSubsequence());
        entity.setLegacyIndicator(dto.getLegacyIndicator());
        entity.setLegacyPercentage(dto.getLegacyPercentage());
        entity.setAcquisitionTypeCode(dto.getAcquisitionTypeCode());
        entity.setHeirName(dto.getHeirName());
        return entity;
    }
}