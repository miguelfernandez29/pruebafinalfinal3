package com.example.app.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class AssetReductionDTO {

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
    @Size(max = 10)
    private String reductionCode;

    @Size(max = 200)
    private String reductionDescription;

    private BigDecimal reductionPercentage;

    private BigDecimal reductionAmount;

    public AssetReductionDTO() {
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

    public String getReductionCode() {
        return reductionCode;
    }

    public void setReductionCode(String reductionCode) {
        this.reductionCode = reductionCode;
    }

    public String getReductionDescription() {
        return reductionDescription;
    }

    public void setReductionDescription(String reductionDescription) {
        this.reductionDescription = reductionDescription;
    }

    public BigDecimal getReductionPercentage() {
        return reductionPercentage;
    }

    public void setReductionPercentage(BigDecimal reductionPercentage) {
        this.reductionPercentage = reductionPercentage;
    }

    public BigDecimal getReductionAmount() {
        return reductionAmount;
    }

    public void setReductionAmount(BigDecimal reductionAmount) {
        this.reductionAmount = reductionAmount;
    }
}