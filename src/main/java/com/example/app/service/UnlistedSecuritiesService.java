package com.example.app.service;

import com.example.app.dto.UnlistedSecuritiesDTO;
import com.example.app.entity.UnlistedSecurities;
import com.example.app.entity.UnlistedSecuritiesId;
import com.example.app.repository.UnlistedSecuritiesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UnlistedSecuritiesService {

    private final UnlistedSecuritiesRepository unlistedSecuritiesRepository;

    public UnlistedSecuritiesService(UnlistedSecuritiesRepository unlistedSecuritiesRepository) {
        this.unlistedSecuritiesRepository = unlistedSecuritiesRepository;
    }

    public List<UnlistedSecuritiesDTO> findAll() {
        return unlistedSecuritiesRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<UnlistedSecuritiesDTO> findById(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, Integer assetSequence) {
        UnlistedSecuritiesId id = new UnlistedSecuritiesId(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
        return unlistedSecuritiesRepository.findById(id).map(this::toDTO);
    }

    public List<UnlistedSecuritiesDTO> findByDeclaration(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif) {
        return unlistedSecuritiesRepository.findByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNif(
                presentationYear, taxType, presentationCode, taxpayerNif)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public UnlistedSecuritiesDTO save(UnlistedSecuritiesDTO dto) {
        validateCompanyCif(dto.getCompanyCif());
        validateNumberOfUnits(dto.getNumberOfUnits());

        UnlistedSecurities entity = toEntity(dto);
        UnlistedSecurities saved = unlistedSecuritiesRepository.save(entity);
        return toDTO(saved);
    }

    public void deleteById(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, Integer assetSequence) {
        UnlistedSecuritiesId id = new UnlistedSecuritiesId(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
        unlistedSecuritiesRepository.deleteById(id);
    }

    private void validateCompanyCif(String companyCif) {
        if (companyCif != null && !companyCif.isEmpty()) {
            if (companyCif.length() != 9) {
                throw new IllegalArgumentException("CIF de empresa no valido.");
            }
        }
    }

    private void validateNumberOfUnits(BigDecimal numberOfUnits) {
        if (numberOfUnits != null && numberOfUnits.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El numero de unidades debe ser positivo.");
        }
    }

    private UnlistedSecuritiesDTO toDTO(UnlistedSecurities entity) {
        UnlistedSecuritiesDTO dto = new UnlistedSecuritiesDTO();
        dto.setPresentationYear(entity.getPresentationYear());
        dto.setTaxType(entity.getTaxType());
        dto.setPresentationCode(entity.getPresentationCode());
        dto.setTaxpayerNif(entity.getTaxpayerNif());
        dto.setAssetSequence(entity.getAssetSequence());
        dto.setCompanyCif(entity.getCompanyCif());
        dto.setAssetDescription(entity.getAssetDescription());
        dto.setTheoreticalValue(entity.getTheoreticalValue());
        dto.setNumberOfUnits(entity.getNumberOfUnits());
        dto.setDeclaredValue(entity.getDeclaredValue());
        dto.setVerifiedValue(entity.getVerifiedValue());
        dto.setConformityIndicator(entity.getConformityIndicator());
        return dto;
    }

    private UnlistedSecurities toEntity(UnlistedSecuritiesDTO dto) {
        UnlistedSecurities entity = new UnlistedSecurities();
        entity.setPresentationYear(dto.getPresentationYear());
        entity.setTaxType(dto.getTaxType());
        entity.setPresentationCode(dto.getPresentationCode());
        entity.setTaxpayerNif(dto.getTaxpayerNif());
        entity.setAssetSequence(dto.getAssetSequence());
        entity.setCompanyCif(dto.getCompanyCif());
        entity.setAssetDescription(dto.getAssetDescription());
        entity.setTheoreticalValue(dto.getTheoreticalValue());
        entity.setNumberOfUnits(dto.getNumberOfUnits());
        entity.setDeclaredValue(dto.getDeclaredValue());
        entity.setVerifiedValue(dto.getVerifiedValue());
        entity.setConformityIndicator(dto.getConformityIndicator());
        return entity;
    }
}