package com.example.app.service;

import com.example.app.dto.BankAccountDTO;
import com.example.app.entity.BankAccount;
import com.example.app.entity.BankAccountId;
import com.example.app.repository.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<BankAccountDTO> findAll() {
        return bankAccountRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<BankAccountDTO> findById(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, Integer assetSequence) {
        BankAccountId id = new BankAccountId(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
        return bankAccountRepository.findById(id).map(this::toDTO);
    }

    public List<BankAccountDTO> findByDeclaration(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif) {
        return bankAccountRepository.findByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNif(
                presentationYear, taxType, presentationCode, taxpayerNif)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public BankAccountDTO save(BankAccountDTO dto) {
        validateIban(dto.getDepositAccount());
        validateDeclaredValue(dto.getDeclaredValue());

        BankAccount entity = toEntity(dto);
        BankAccount saved = bankAccountRepository.save(entity);
        return toDTO(saved);
    }

    public void deleteById(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, Integer assetSequence) {
        BankAccountId id = new BankAccountId(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
        bankAccountRepository.deleteById(id);
    }

    private void validateIban(String iban) {
        if (iban != null && !iban.isEmpty()) {
            String cleaned = iban.replaceAll("\\s", "").toUpperCase();
            if (cleaned.length() < 15 || cleaned.length() > 34) {
                throw new IllegalArgumentException("Formato de IBAN no valido.");
            }
        }
    }

    private void validateDeclaredValue(BigDecimal declaredValue) {
        if (declaredValue != null && declaredValue.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El valor declarado debe ser positivo.");
        }
    }

    private BankAccountDTO toDTO(BankAccount entity) {
        BankAccountDTO dto = new BankAccountDTO();
        dto.setPresentationYear(entity.getPresentationYear());
        dto.setTaxType(entity.getTaxType());
        dto.setPresentationCode(entity.getPresentationCode());
        dto.setTaxpayerNif(entity.getTaxpayerNif());
        dto.setAssetSequence(entity.getAssetSequence());
        dto.setBankEntity(entity.getBankEntity());
        dto.setDepositAccount(entity.getDepositAccount());
        dto.setDeclaredValue(entity.getDeclaredValue());
        dto.setVerifiedValue(entity.getVerifiedValue());
        dto.setConformityIndicator(entity.getConformityIndicator());
        return dto;
    }

    private BankAccount toEntity(BankAccountDTO dto) {
        BankAccount entity = new BankAccount();
        entity.setPresentationYear(dto.getPresentationYear());
        entity.setTaxType(dto.getTaxType());
        entity.setPresentationCode(dto.getPresentationCode());
        entity.setTaxpayerNif(dto.getTaxpayerNif());
        entity.setAssetSequence(dto.getAssetSequence());
        entity.setBankEntity(dto.getBankEntity());
        entity.setDepositAccount(dto.getDepositAccount());
        entity.setDeclaredValue(dto.getDeclaredValue());
        entity.setVerifiedValue(dto.getVerifiedValue());
        entity.setConformityIndicator(dto.getConformityIndicator());
        return entity;
    }
}