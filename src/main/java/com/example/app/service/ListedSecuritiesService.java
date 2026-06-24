package com.example.app.service;

import com.example.app.dto.ListedSecuritiesDTO;
import com.example.app.entity.ListedSecurities;
import com.example.app.entity.ListedSecuritiesId;
import com.example.app.repository.ListedSecuritiesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ListedSecuritiesService {

    private final ListedSecuritiesRepository listedSecuritiesRepository;

    public ListedSecuritiesService(ListedSecuritiesRepository listedSecuritiesRepository) {
        this.listedSecuritiesRepository = listedSecuritiesRepository;
    }

    public List<ListedSecuritiesDTO> findAll() {
        return listedSecuritiesRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ListedSecuritiesDTO> findById(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, Integer assetSequence) {
        ListedSecuritiesId id = new ListedSecuritiesId(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
        return listedSecuritiesRepository.findById(id).map(this::toDTO);
    }

    public List<ListedSecuritiesDTO> findByDeclaration(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif) {
        return listedSecuritiesRepository.findByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNif(
                presentationYear, taxType, presentationCode, taxpayerNif)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ListedSecuritiesDTO save(ListedSecuritiesDTO dto) {
        validateQuotationPrice(dto.getQuotationPrice());
        validateNumberOfUnits(dto.getNumberOfUnits());

        ListedSecurities entity = toEntity(dto);
        ListedSecurities saved = listedSecuritiesRepository.save(entity);
        return toDTO(saved);
    }

    public void deleteById(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, Integer assetSequence) {
        ListedSecuritiesId id = new ListedSecuritiesId(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
        listedSecuritiesRepository.deleteById(id);
    }

    private void validateQuotationPrice(BigDecimal quotationPrice) {
        if (quotationPrice != null && quotationPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio de cotizacion debe ser positivo.");
        }
    }

    private void validateNumberOfUnits(BigDecimal numberOfUnits) {
        if (numberOfUnits != null && numberOfUnits.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El numero de unidades debe ser positivo.");
        }
    }

    private ListedSecuritiesDTO toDTO(ListedSecurities entity) {
        ListedSecuritiesDTO dto = new ListedSecuritiesDTO();
        dto.setPresentationYear(entity.getPresentationYear());
        dto.setTaxType(entity.getTaxType());
        dto.setPresentationCode(entity.getPresentationCode());
        dto.setTaxpayerNif(entity.getTaxpayerNif());
        dto.setAssetSequence(entity.getAssetSequence());
        dto.setAssetDescription(entity.getAssetDescription());
        dto.setQuotationPrice(entity.getQuotationPrice());
        dto.setNumberOfUnits(entity.getNumberOfUnits());
        dto.setDeclaredValue(entity.getDeclaredValue());
        dto.setVerifiedValue(entity.getVerifiedValue());
        dto.setConformityIndicator(entity.getConformityIndicator());
        return dto;
    }

    private ListedSecurities toEntity(ListedSecuritiesDTO dto) {
        ListedSecurities entity = new ListedSecurities();
        entity.setPresentationYear(dto.getPresentationYear());
        entity.setTaxType(dto.getTaxType());
        entity.setPresentationCode(dto.getPresentationCode());
        entity.setTaxpayerNif(dto.getTaxpayerNif());
        entity.setAssetSequence(dto.getAssetSequence());
        entity.setAssetDescription(dto.getAssetDescription());
        entity.setQuotationPrice(dto.getQuotationPrice());
        entity.setNumberOfUnits(dto.getNumberOfUnits());
        entity.setDeclaredValue(dto.getDeclaredValue());
        entity.setVerifiedValue(dto.getVerifiedValue());
        entity.setConformityIndicator(dto.getConformityIndicator());
        return entity;
    }
}