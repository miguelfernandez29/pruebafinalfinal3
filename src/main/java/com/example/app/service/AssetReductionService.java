package com.example.app.service;

import com.example.app.dto.AssetReductionDTO;
import com.example.app.entity.AssetReduction;
import com.example.app.entity.AssetReductionId;
import com.example.app.repository.AssetReductionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AssetReductionService {

    private final AssetReductionRepository assetReductionRepository;

    public AssetReductionService(AssetReductionRepository assetReductionRepository) {
        this.assetReductionRepository = assetReductionRepository;
    }

    public List<AssetReductionDTO> findAll() {
        return assetReductionRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<AssetReductionDTO> findById(Integer presentationYear, String taxType, String presentationCode,
                                                 String taxpayerNif, Integer assetSequence, String reductionCode) {
        AssetReductionId id = new AssetReductionId(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence, reductionCode);
        return assetReductionRepository.findById(id).map(this::toDTO);
    }

    public List<AssetReductionDTO> findByAsset(Integer presentationYear, String taxType, String presentationCode,
                                                String taxpayerNif, Integer assetSequence) {
        return assetReductionRepository.findByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNifAndAssetSequence(
                presentationYear, taxType, presentationCode, taxpayerNif, assetSequence)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public boolean hasReductions(Integer presentationYear, String taxType, String presentationCode,
                                  String taxpayerNif, Integer assetSequence) {
        return assetReductionRepository.existsReductionForAsset(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
    }

    public AssetReductionDTO save(AssetReductionDTO dto) {
        AssetReduction entity = toEntity(dto);
        AssetReduction saved = assetReductionRepository.save(entity);
        return toDTO(saved);
    }

    public void deleteById(Integer presentationYear, String taxType, String presentationCode,
                           String taxpayerNif, Integer assetSequence, String reductionCode) {
        AssetReductionId id = new AssetReductionId(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence, reductionCode);
        assetReductionRepository.deleteById(id);
    }

    public void deleteByAsset(Integer presentationYear, String taxType, String presentationCode,
                              String taxpayerNif, Integer assetSequence) {
        assetReductionRepository.deleteByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNifAndAssetSequence(
                presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
    }

    private AssetReductionDTO toDTO(AssetReduction entity) {
        AssetReductionDTO dto = new AssetReductionDTO();
        dto.setPresentationYear(entity.getPresentationYear());
        dto.setTaxType(entity.getTaxType());
        dto.setPresentationCode(entity.getPresentationCode());
        dto.setTaxpayerNif(entity.getTaxpayerNif());
        dto.setAssetSequence(entity.getAssetSequence());
        dto.setReductionCode(entity.getReductionCode());
        dto.setReductionDescription(entity.getReductionDescription());
        dto.setReductionPercentage(entity.getReductionPercentage());
        dto.setReductionAmount(entity.getReductionAmount());
        return dto;
    }

    private AssetReduction toEntity(AssetReductionDTO dto) {
        AssetReduction entity = new AssetReduction();
        entity.setPresentationYear(dto.getPresentationYear());
        entity.setTaxType(dto.getTaxType());
        entity.setPresentationCode(dto.getPresentationCode());
        entity.setTaxpayerNif(dto.getTaxpayerNif());
        entity.setAssetSequence(dto.getAssetSequence());
        entity.setReductionCode(dto.getReductionCode());
        entity.setReductionDescription(dto.getReductionDescription());
        entity.setReductionPercentage(dto.getReductionPercentage());
        entity.setReductionAmount(dto.getReductionAmount());
        return entity;
    }
}