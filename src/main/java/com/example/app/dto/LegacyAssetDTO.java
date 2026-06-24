package com.example.app.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class LegacyAssetDTO {

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

    @NotNull
    @Size(max = 9)
    private String heirNif;

    @NotNull
    @Size(max = 2)
    private String heirSubsequence;

    @Size(max = 1)
    private String legacyIndicator;

    private BigDecimal legacyPercentage;

    @Size(max = 2)
    private String acquisitionTypeCode;

    @Size(max = 100)
    private String heirName;

    public LegacyAssetDTO() {
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

    public String getHeirNif() {
        return heirNif;
    }

    public void setHeirNif(String heirNif) {
        this.heirNif = heirNif;
    }

    public String getHeirSubsequence() {
        return heirSubsequence;
    }

    public void setHeirSubsequence(String heirSubsequence) {
        this.heirSubsequence = heirSubsequence;
    }

    public String getLegacyIndicator() {
        return legacyIndicator;
    }

    public void setLegacyIndicator(String legacyIndicator) {
        this.legacyIndicator = legacyIndicator;
    }

    public BigDecimal getLegacyPercentage() {
        return legacyPercentage;
    }

    public void setLegacyPercentage(BigDecimal legacyPercentage) {
        this.legacyPercentage = legacyPercentage;
    }

    public String getAcquisitionTypeCode() {
        return acquisitionTypeCode;
    }

    public void setAcquisitionTypeCode(String acquisitionTypeCode) {
        this.acquisitionTypeCode = acquisitionTypeCode;
    }

    public String getHeirName() {
        return heirName;
    }

    public void setHeirName(String heirName) {
        this.heirName = heirName;
    }
}