package com.example.app.service;

import com.example.app.dto.ListedSecuritiesDto;
import com.example.app.entity.ListedSecurities;
import com.example.app.entity.ListedSecuritiesId;
import com.example.app.exception.BusinessValidationException;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.repository.ListedSecuritiesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ListedSecuritiesService {

    private final ListedSecuritiesRepository listedSecuritiesRepository;

    public ListedSecuritiesService(ListedSecuritiesRepository listedSecuritiesRepository) {
        this.listedSecuritiesRepository = listedSecuritiesRepository;
    }

    public List<ListedSecuritiesDto> findAll() {
        return listedSecuritiesRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ListedSecuritiesDto findById(Integer presentationYear, String taxTypeCode, String presentationCode,
                                         String causeNif, String subCauseCode, Integer assetSequence) {
        ListedSecuritiesId id = new ListedSecuritiesId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        ListedSecurities entity = listedSecuritiesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ListedSecurities", "id", id));
        return toDto(entity);
    }

    public ListedSecuritiesDto create(ListedSecuritiesDto dto) {
        validateListedSecurities(dto);
        ListedSecurities entity = toEntity(dto);
        calculateVerifiedValue(entity);
        ListedSecurities saved = listedSecuritiesRepository.save(entity);
        return toDto(saved);
    }

    public ListedSecuritiesDto update(Integer presentationYear, String taxTypeCode, String presentationCode,
                                       String causeNif, String subCauseCode, Integer assetSequence,
                                       ListedSecuritiesDto dto) {
        ListedSecuritiesId id = new ListedSecuritiesId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        ListedSecurities existing = listedSecuritiesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ListedSecurities", "id", id));
        
        validateListedSecurities(dto);
        updateEntityFromDto(existing, dto);
        calculateVerifiedValue(existing);
        
        ListedSecurities saved = listedSecuritiesRepository.save(existing);
        return toDto(saved);
    }

    public void delete(Integer presentationYear, String taxTypeCode, String presentationCode,
                       String causeNif, String subCauseCode, Integer assetSequence) {
        ListedSecuritiesId id = new ListedSecuritiesId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        ListedSecurities existing = listedSecuritiesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ListedSecurities", "id", id));
        listedSecuritiesRepository.delete(existing);
    }

    public List<ListedSecuritiesDto> findByIsinCode(String isinCode) {
        return listedSecuritiesRepository.findByIsinCode(isinCode).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private void validateListedSecurities(ListedSecuritiesDto dto) {
        if (dto.getIsinCode() != null && dto.getIsinCode().length() != 12) {
            throw new BusinessValidationException("INVALID_ISIN", "isinCode",
                    "ISIN code must be exactly 12 characters");
        }
        
        if (dto.getNumberOfShares() != null && dto.getNumberOfShares() <= 0) {
            throw new BusinessValidationException("INVALID_SHARES", "numberOfShares",
                    "Number of shares must be positive");
        }
        
        if (dto.getQuotationPrice() != null && dto.getQuotationPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessValidationException("INVALID_PRICE", "quotationPrice",
                    "Quotation price must be positive");
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

    private void calculateVerifiedValue(ListedSecurities entity) {
        if (entity.getNumberOfShares() != null && entity.getQuotationPrice() != null) {
            BigDecimal totalValue = entity.getQuotationPrice()
                    .multiply(new BigDecimal(entity.getNumberOfShares()));
            entity.setVerifiedValue(totalValue.setScale(2, RoundingMode.HALF_UP));
        }
    }

    private ListedSecuritiesDto toDto(ListedSecurities entity) {
        ListedSecuritiesDto dto = new ListedSecuritiesDto();
        dto.setPresentationYear(entity.getPresentationYear());
        dto.setTaxTypeCode(entity.getTaxTypeCode());
        dto.setPresentationCode(entity.getPresentationCode());
        dto.setCauseNif(entity.getCauseNif());
        dto.setSubCauseCode(entity.getSubCauseCode());
        dto.setAssetSequence(entity.getAssetSequence());
        dto.setAssetTypeCode(entity.getAssetTypeCode());
        dto.setAssetTypeDescription(entity.getAssetTypeDescription());
        dto.setIsinCode(entity.getIsinCode());
        dto.setSecurityName(entity.getSecurityName());
        dto.setNumberOfShares(entity.getNumberOfShares());
        dto.setQuotationPrice(entity.getQuotationPrice());
        dto.setQuotationDate(entity.getQuotationDate());
        dto.setDeclaredValue(entity.getDeclaredValue());
        dto.setVerifiedValue(entity.getVerifiedValue());
        dto.setParticipationPercentage(entity.getParticipationPercentage());
        return dto;
    }

    private ListedSecurities toEntity(ListedSecuritiesDto dto) {
        ListedSecurities entity = new ListedSecurities();
        entity.setPresentationYear(dto.getPresentationYear());
        entity.setTaxTypeCode(dto.getTaxTypeCode());
        entity.setPresentationCode(dto.getPresentationCode());
        entity.setCauseNif(dto.getCauseNif());
        entity.setSubCauseCode(dto.getSubCauseCode());
        entity.setAssetSequence(dto.getAssetSequence());
        updateEntityFromDto(entity, dto);
        return entity;
    }

    private void updateEntityFromDto(ListedSecurities entity, ListedSecuritiesDto dto) {
        entity.setAssetTypeCode(dto.getAssetTypeCode());
        entity.setAssetTypeDescription(dto.getAssetTypeDescription());
        entity.setIsinCode(dto.getIsinCode());
        entity.setSecurityName(dto.getSecurityName());
        entity.setNumberOfShares(dto.getNumberOfShares());
        entity.setQuotationPrice(dto.getQuotationPrice());
        entity.setQuotationDate(dto.getQuotationDate());
        entity.setDeclaredValue(dto.getDeclaredValue());
        entity.setVerifiedValue(dto.getVerifiedValue());
        entity.setParticipationPercentage(dto.getParticipationPercentage());
    }
}