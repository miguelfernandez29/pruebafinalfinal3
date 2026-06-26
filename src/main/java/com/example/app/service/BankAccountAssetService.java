package com.example.app.service;

import com.example.app.dto.BankAccountAssetDto;
import com.example.app.entity.BankAccountAsset;
import com.example.app.entity.BankAccountAssetId;
import com.example.app.exception.BusinessValidationException;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.repository.BankAccountAssetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BankAccountAssetService {

    private final BankAccountAssetRepository bankAccountAssetRepository;

    public BankAccountAssetService(BankAccountAssetRepository bankAccountAssetRepository) {
        this.bankAccountAssetRepository = bankAccountAssetRepository;
    }

    public List<BankAccountAssetDto> findAll() {
        return bankAccountAssetRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public BankAccountAssetDto findById(Integer presentationYear, String taxTypeCode, String presentationCode,
                                         String causeNif, String subCauseCode, Integer assetSequence) {
        BankAccountAssetId id = new BankAccountAssetId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        BankAccountAsset entity = bankAccountAssetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BankAccountAsset", "id", id));
        return toDto(entity);
    }

    public BankAccountAssetDto create(BankAccountAssetDto dto) {
        validateBankAccountAsset(dto);
        BankAccountAsset entity = toEntity(dto);
        BankAccountAsset saved = bankAccountAssetRepository.save(entity);
        return toDto(saved);
    }

    public BankAccountAssetDto update(Integer presentationYear, String taxTypeCode, String presentationCode,
                                       String causeNif, String subCauseCode, Integer assetSequence,
                                       BankAccountAssetDto dto) {
        BankAccountAssetId id = new BankAccountAssetId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        BankAccountAsset existing = bankAccountAssetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BankAccountAsset", "id", id));
        
        validateBankAccountAsset(dto);
        updateEntityFromDto(existing, dto);
        
        BankAccountAsset saved = bankAccountAssetRepository.save(existing);
        return toDto(saved);
    }

    public void delete(Integer presentationYear, String taxTypeCode, String presentationCode,
                       String causeNif, String subCauseCode, Integer assetSequence) {
        BankAccountAssetId id = new BankAccountAssetId(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        BankAccountAsset existing = bankAccountAssetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BankAccountAsset", "id", id));
        bankAccountAssetRepository.delete(existing);
    }

    public List<BankAccountAssetDto> findByDepositAccount(String depositAccount) {
        return bankAccountAssetRepository.findByDepositAccount(depositAccount).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private void validateBankAccountAsset(BankAccountAssetDto dto) {
        if (dto.getDepositAccount() != null) {
            String iban = dto.getDepositAccount().replaceAll("\\s+", "").toUpperCase();
            if (iban.length() < 15 || iban.length() > 34) {
                throw new BusinessValidationException("INVALID_IBAN", "depositAccount",
                        "IBAN must be between 15 and 34 characters");
            }
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

    private BankAccountAssetDto toDto(BankAccountAsset entity) {
        BankAccountAssetDto dto = new BankAccountAssetDto();
        dto.setPresentationYear(entity.getPresentationYear());
        dto.setTaxTypeCode(entity.getTaxTypeCode());
        dto.setPresentationCode(entity.getPresentationCode());
        dto.setCauseNif(entity.getCauseNif());
        dto.setSubCauseCode(entity.getSubCauseCode());
        dto.setAssetSequence(entity.getAssetSequence());
        dto.setAssetTypeCode(entity.getAssetTypeCode());
        dto.setAssetTypeDescription(entity.getAssetTypeDescription());
        dto.setDepositAccount(entity.getDepositAccount());
        dto.setBankEntity(entity.getBankEntity());
        dto.setDescription(entity.getDescription());
        dto.setDeclaredValue(entity.getDeclaredValue());
        dto.setVerifiedValue(entity.getVerifiedValue());
        dto.setParticipationPercentage(entity.getParticipationPercentage());
        return dto;
    }

    private BankAccountAsset toEntity(BankAccountAssetDto dto) {
        BankAccountAsset entity = new BankAccountAsset();
        entity.setPresentationYear(dto.getPresentationYear());
        entity.setTaxTypeCode(dto.getTaxTypeCode());
        entity.setPresentationCode(dto.getPresentationCode());
        entity.setCauseNif(dto.getCauseNif());
        entity.setSubCauseCode(dto.getSubCauseCode());
        entity.setAssetSequence(dto.getAssetSequence());
        updateEntityFromDto(entity, dto);
        return entity;
    }

    private void updateEntityFromDto(BankAccountAsset entity, BankAccountAssetDto dto) {
        entity.setAssetTypeCode(dto.getAssetTypeCode());
        entity.setAssetTypeDescription(dto.getAssetTypeDescription());
        entity.setDepositAccount(dto.getDepositAccount());
        entity.setBankEntity(dto.getBankEntity());
        entity.setDescription(dto.getDescription());
        entity.setDeclaredValue(dto.getDeclaredValue());
        entity.setVerifiedValue(dto.getVerifiedValue());
        entity.setParticipationPercentage(dto.getParticipationPercentage());
    }
}