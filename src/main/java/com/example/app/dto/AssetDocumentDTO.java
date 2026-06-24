package com.example.app.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class AssetDocumentDTO {

    @NotNull
    private Integer presentationYear;

    @NotNull
    @Size(max = 2)
    private String taxType;

    @NotNull
    @Size(max = 14)
    private String presentationCode;

    @NotNull
    @Size(max = 9)
    private String taxpayerNif;

    @NotNull
    private Integer assetSequence;

    @Size(max = 2)
    private String assetNatureCode;

    @Size(max = 2)
    private String assetPositionCode;

    private BigDecimal transmissionPercentage;

    private BigDecimal declaredValue;

    private BigDecimal verifiedValue;

    @Size(max = 1)
    private String conformityIndicator;

    @Size(max = 1)
    private String duplicateIndicator;

    @Size(max = 2)
    private String situationCode;

    @Size(max = 100)
    private String situationDescription;

    public AssetDocumentDTO() {
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

    public String getAssetNatureCode() {
        return assetNatureCode;
    }

    public void setAssetNatureCode(String assetNatureCode) {
        this.assetNatureCode = assetNatureCode;
    }

    public String getAssetPositionCode() {
        return assetPositionCode;
    }

    public void setAssetPositionCode(String assetPositionCode) {
        this.assetPositionCode = assetPositionCode;
    }

    public BigDecimal getTransmissionPercentage() {
        return transmissionPercentage;
    }

    public void setTransmissionPercentage(BigDecimal transmissionPercentage) {
        this.transmissionPercentage = transmissionPercentage;
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

    public String getDuplicateIndicator() {
        return duplicateIndicator;
    }

    public void setDuplicateIndicator(String duplicateIndicator) {
        this.duplicateIndicator = duplicateIndicator;
    }

    public String getSituationCode() {
        return situationCode;
    }

    public void setSituationCode(String situationCode) {
        this.situationCode = situationCode;
    }

    public String getSituationDescription() {
        return situationDescription;
    }

    public void setSituationDescription(String situationDescription) {
        this.situationDescription = situationDescription;
    }
}