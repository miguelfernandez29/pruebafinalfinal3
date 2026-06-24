package com.example.app.service;

import com.example.app.dto.AssetDocumentDTO;
import com.example.app.entity.AssetDocument;
import com.example.app.entity.AssetDocumentId;
import com.example.app.repository.AssetDocumentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AssetDocumentService {

    private final AssetDocumentRepository assetDocumentRepository;

    public AssetDocumentService(AssetDocumentRepository assetDocumentRepository) {
        this.assetDocumentRepository = assetDocumentRepository;
    }

    public List<AssetDocumentDTO> findAll() {
        return assetDocumentRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<AssetDocumentDTO> findById(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, Integer assetSequence) {
        AssetDocumentId id = new AssetDocumentId(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
        return assetDocumentRepository.findById(id).map(this::toDTO);
    }

    public List<AssetDocumentDTO> findByDeclaration(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif) {
        return assetDocumentRepository.findByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNif(
                presentationYear, taxType, presentationCode, taxpayerNif)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AssetDocumentDTO save(AssetDocumentDTO dto) {
        AssetDocument entity = toEntity(dto);
        AssetDocument saved = assetDocumentRepository.save(entity);
        return toDTO(saved);
    }

    public void deleteById(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, Integer assetSequence) {
        AssetDocumentId id = new AssetDocumentId(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
        assetDocumentRepository.deleteById(id);
    }

    private AssetDocumentDTO toDTO(AssetDocument entity) {
        AssetDocumentDTO dto = new AssetDocumentDTO();
        dto.setPresentationYear(entity.getPresentationYear());
        dto.setTaxType(entity.getTaxType());
        dto.setPresentationCode(entity.getPresentationCode());
        dto.setTaxpayerNif(entity.getTaxpayerNif());
        dto.setAssetSequence(entity.getAssetSequence());
        dto.setAssetNatureCode(entity.getAssetNatureCode());
        dto.setAssetPositionCode(entity.getAssetPositionCode());
        dto.setTransmissionPercentage(entity.getTransmissionPercentage());
        dto.setDeclaredValue(entity.getDeclaredValue());
        dto.setVerifiedValue(entity.getVerifiedValue());
        dto.setConformityIndicator(entity.getConformityIndicator());
        dto.setDuplicateIndicator(entity.getDuplicateIndicator());
        dto.setSituationCode(entity.getSituationCode());
        dto.setSituationDescription(entity.getSituationDescription());
        return dto;
    }

    private AssetDocument toEntity(AssetDocumentDTO dto) {
        AssetDocument entity = new AssetDocument();
        entity.setPresentationYear(dto.getPresentationYear());
        entity.setTaxType(dto.getTaxType());
        entity.setPresentationCode(dto.getPresentationCode());
        entity.setTaxpayerNif(dto.getTaxpayerNif());
        entity.setAssetSequence(dto.getAssetSequence());
        entity.setAssetNatureCode(dto.getAssetNatureCode());
        entity.setAssetPositionCode(dto.getAssetPositionCode());
        entity.setTransmissionPercentage(dto.getTransmissionPercentage());
        entity.setDeclaredValue(dto.getDeclaredValue());
        entity.setVerifiedValue(dto.getVerifiedValue());
        entity.setConformityIndicator(dto.getConformityIndicator());
        entity.setDuplicateIndicator(dto.getDuplicateIndicator());
        entity.setSituationCode(dto.getSituationCode());
        entity.setSituationDescription(dto.getSituationDescription());
        return entity;
    }
}