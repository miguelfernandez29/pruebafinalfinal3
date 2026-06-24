package com.example.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "GATA_BIENCUBA")
@IdClass(BankAccountId.class)
public class BankAccount {

    @Id
    @Column(name = "AAPRESENTA")
    private Integer presentationYear;

    @Id
    @Column(name = "VFTIPOIMPU")
    private String taxType;

    @Id
    @Column(name = "CDPRESENTA")
    private String presentationCode;

    @Id
    @Column(name = "CDNIFCAUSA")
    private String taxpayerNif;

    @Id
    @Column(name = "CDSECUBIEN")
    private Integer assetSequence;

    @Column(name = "TLENTIDEPO")
    private String bankEntity;

    @Column(name = "TLDEPOSITO")
    private String depositAccount;

    @Column(name = "PTDECLARAD")
    private BigDecimal declaredValue;

    @Column(name = "PTCOMPROBA")
    private BigDecimal verifiedValue;

    @Column(name = "ITCONFORME")
    private String conformityIndicator;

    public BankAccount() {
    }

    public Integer getPresentationYear() {
        return presentationYear;
    }

    public void setPresentationYear(Integer presentationYear) {
        this.presentationYear = presentationYear;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getPresentationCode() {
        return presentationCode;
    }

    public void setPresentationCode(String presentationCode) {
        this.presentationCode = presentationCode;
    }

    public String getTaxpayerNif() {
        return taxpayerNif;
    }

    public void setTaxpayerNif(String taxpayerNif) {
        this.taxpayerNif = taxpayerNif;
    }

    public Integer getAssetSequence() {
        return assetSequence;
    }

    public void setAssetSequence(Integer assetSequence) {
        this.assetSequence = assetSequence;
    }

    public String getBankEntity() {
        return bankEntity;
    }

    public void setBankEntity(String bankEntity) {
        this.bankEntity = bankEntity;
    }

    public String getDepositAccount() {
        return depositAccount;
    }

    public void setDepositAccount(String depositAccount) {
        this.depositAccount = depositAccount;
    }

    public BigDecimal getDeclaredValue() {
        return declaredValue;
    }

    public void setDeclaredValue(BigDecimal declaredValue) {
        this.declaredValue = declaredValue;
    }

    public BigDecimal getVerifiedValue() {
        return verifiedValue;
    }

    public void setVerifiedValue(BigDecimal verifiedValue) {
        this.verifiedValue = verifiedValue;
    }

    public String getConformityIndicator() {
        return conformityIndicator;
    }

    public void setConformityIndicator(String conformityIndicator) {
        this.conformityIndicator = conformityIndicator;
    }
}