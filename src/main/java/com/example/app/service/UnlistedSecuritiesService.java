package com.example.app.service;

import com.example.app.dto.UnlistedSecuritiesDto;
import com.example.app.entity.UnlistedSecurities;
import com.example.app.entity.UnlistedSecuritiesId;
import com.example.app.exception.BusinessValidationException;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.repository.UnlistedSecuritiesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UnlistedSecuritiesService {

    private final UnlistedSecuritiesRepository unlistedSecuritiesRepository;

    public UnlistedSecuritiesService(UnlistedSecuritiesRepository unlistedSecuritiesRepository) {
        this.unlistedSecuritiesRepository = unlistedSecuritiesRepository;
    }

    public List<UnlistedSecuritiesDto> findAll() {
        return unlistedSecuritiesRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public UnlistedSecuritiesDto findById(Integer presentationYear, String taxTypeCode, String presentationCode,
                                           String causeNif, String subCauseCode, Integer assetSequence) {
        UnlistedSecuritiesId id = new UnlistedSecuritiesId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        UnlistedSecurities entity = unlistedSecuritiesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UnlistedSecurities", "id", id));
        return toDto(entity);
    }

    public UnlistedSecuritiesDto create(UnlistedSecuritiesDto dto) {
        validateUnlistedSecurities(dto);
        UnlistedSecurities entity = toEntity(dto);
        calculateVerifiedValue(entity);
        UnlistedSecurities saved = unlistedSecuritiesRepository.save(entity);
        return toDto(saved);
    }

    public UnlistedSecuritiesDto update(Integer presentationYear, String taxTypeCode, String presentationCode,
                                         String causeNif, String subCauseCode, Integer assetSequence,
                                         UnlistedSecuritiesDto dto) {
        UnlistedSecuritiesId id = new UnlistedSecuritiesId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        UnlistedSecurities existing = unlistedSecuritiesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UnlistedSecurities", "id", id));
        
        validateUnlistedSecurities(dto);
        updateEntityFromDto(existing, dto);
        calculateVerifiedValue(existing);
        
        UnlistedSecurities saved = unlistedSecuritiesRepository.save(existing);
        return toDto(saved);
    }

    public void delete(Integer presentationYear, String taxTypeCode, String presentationCode,
                       String causeNif, String subCauseCode, Integer assetSequence) {
        UnlistedSecuritiesId id = new UnlistedSecuritiesId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        UnlistedSecurities existing = unlistedSecuritiesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UnlistedSecurities", "id", id));
        unlistedSecuritiesRepository.delete(existing);
    }

    public List<UnlistedSecuritiesDto> findByCompanyCif(String companyCif) {
        return unlistedSecuritiesRepository.findByCompanyCif(companyCif).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private void validateUnlistedSecurities(UnlistedSecuritiesDto dto) {
        if (dto.getCompanyCif() != null && dto.getCompanyCif().length() > 9) {
            throw new BusinessValidationException("INVALID_CIF", "companyCif",
                    "Company CIF must be 9 characters or less");
        }
        
        if (dto.getNumberOfShares() != null && dto.getNumberOfShares() <= 0) {
            throw new BusinessValidationException("INVALID_SHARES", "numberOfShares",
                    "Number of shares must be positive");
        }
        
        if (dto.getNominalValue() != null && dto.getNominalValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessValidationException("INVALID_VALUE", "nominalValue",
                    "Nominal value must be positive");
        }
        
        if (dto.getRealTheoreticalValue() != null && dto.getRealTheoreticalValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessValidationException("INVALID_VALUE", "realTheoreticalValue",
                    "Real theoretical value must be positive");
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

    private void calculateVerifiedValue(UnlistedSecurities entity) {
        if (entity.getNumberOfShares() != null && entity.getRealTheoreticalValue() != null) {
            BigDecimal totalValue = entity.getRealTheoreticalValue()
                    .multiply(new BigDecimal(entity.getNumberOfShares()));
            entity.setVerifiedValue(totalValue.setScale(2, RoundingMode.HALF_UP));
        }
    }

    private UnlistedSecuritiesDto toDto(UnlistedSecurities entity) {
        UnlistedSecuritiesDto dto = new UnlistedSecuritiesDto();
        dto.setPresentationYear(entity.getPresentationYear());
        dto.setTaxTypeCode(entity.getTaxTypeCode());
        dto.setPresentationCode(entity.getPresentationCode());
        dto.setCauseNif(entity.getCauseNif());
        dto.setSubCauseCode(entity.getSubCauseCode());
        dto.setAssetSequence(entity.getAssetSequence());
        dto.setAssetTypeCode(entity.getAssetTypeCode());
        dto.setAssetTypeDescription(entity.getAssetTypeDescription());
        dto.setCompanyCif(entity.getCompanyCif());
        dto.setCompanyName(entity.getCompanyName());
        dto.setNumberOfShares(entity.getNumberOfShares());
        dto.setNominalValue(entity.getNominalValue());
        dto.setRealTheoreticalValue(entity.getRealTheoreticalValue());
        dto.setDeclaredValue(entity.getDeclaredValue());
        dto.setVerifiedValue(entity.getVerifiedValue());
        dto.setParticipationPercentage(entity.getParticipationPercentage());
        return dto;
    }

    private UnlistedSecurities toEntity(UnlistedSecuritiesDto dto) {
        UnlistedSecurities entity = new UnlistedSecurities();
        entity.setPresentationYear(dto.getPresentationYear());
        entity.setTaxTypeCode(dto.getTaxTypeCode());
        entity.setPresentationCode(dto.getPresentationCode());
        entity.setCauseNif(dto.getCauseNif());
        entity.setSubCauseCode(dto.getSubCauseCode());
        entity.setAssetSequence(dto.getAssetSequence());
        updateEntityFromDto(entity, dto);
        return entity;
    }

    private void updateEntityFromDto(UnlistedSecurities entity, UnlistedSecuritiesDto dto) {
        entity.setAssetTypeCode(dto.getAssetTypeCode());
        entity.setAssetTypeDescription(dto.getAssetTypeDescription());
        entity.setCompanyCif(dto.getCompanyCif());
        entity.setCompanyName(dto.getCompanyName());
        entity.setNumberOfShares(dto.getNumberOfShares());
        entity.setNominalValue(dto.getNominalValue());
        entity.setRealTheoreticalValue(dto.getRealTheoreticalValue());
        entity.setDeclaredValue(dto.getDeclaredValue());
        entity.setVerifiedValue(dto.getVerifiedValue());
        entity.setParticipationPercentage(dto.getParticipationPercentage());
    }
}