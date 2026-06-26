package com.example.app.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class AssetCauseDto {

    @NotNull
    private Integer presentationYear;

    @NotNull
    @Size(max = 2)
    private String taxTypeCode;

    @NotNull
    @Size(max = 14)
    private String presentationCode;

    @NotNull
    @Size(max = 9)
    private String causeNif;

    @NotNull
    @Size(max = 2)
    private String subCauseCode;

    @NotNull
    private Integer assetSequence;

    @Size(max = 1)
    private String assetNatureCode;

    @Size(max = 1)
    private String assetPositionCode;

    @Size(max = 3)
    private String assetTypeCode;

    @Size(max = 100)
    private String assetTypeDescription;

    @DecimalMin("0")
    @DecimalMax("100")
    private BigDecimal transmissionPercentage;

    private BigDecimal declaredValue;

    private BigDecimal verifiedValue;

    private BigDecimal proportionalVerifiedValue;

    @Size(max = 1)
    private String conformityIndicator;

    @Size(max = 1)
    private String reductionIndicator;

    @Size(max = 1)
    private String referenceValueIndicator;

    private BigDecimal referenceValue;

    @Size(max = 2)
    private String referenceValueSituation;

    @Size(max = 1)
    private String validReferenceValueIndicator;

    @Size(max = 1)
    private String bdbiValueIndicator;

    @Size(max = 1)
    private String businessAffectedIndicator;

    @Size(max = 1)
    private String reductionAppliedIndicator;

    public AssetCauseDto() {
    }

    public Integer getPresentationYear() {
        return presentationYear;
    }

    public void setPresentationYear(Integer presentationYear) {
        this.presentationYear = presentationYear;
    }

    public String getTaxTypeCode() {
        return taxTypeCode;
    }

    public void setTaxTypeCode(String taxTypeCode) {
        this.taxTypeCode = taxTypeCode;
    }

    public String getPresentationCode() {
        return presentationCode;
    }

    public void setPresentationCode(String presentationCode) {
        this.presentationCode = presentationCode;
    }

    public String getCauseNif() {
        return causeNif;
    }

    public void setCauseNif(String causeNif) {
        this.causeNif = causeNif;
    }

    public String getSubCauseCode() {
        return subCauseCode;
    }

    public void setSubCauseCode(String subCauseCode) {
        this.subCauseCode = subCauseCode;
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

    public String getAssetTypeCode() {
        return assetTypeCode;
    }

    public void setAssetTypeCode(String assetTypeCode) {
        this.assetTypeCode = assetTypeCode;
    }

    public String getAssetTypeDescription() {
        return assetTypeDescription;
    }

    public void setAssetTypeDescription(String assetTypeDescription) {
        this.assetTypeDescription = assetTypeDescription;
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

    public BigDecimal getProportionalVerifiedValue() {
        return proportionalVerifiedValue;
    }

    public void setProportionalVerifiedValue(BigDecimal proportionalVerifiedValue) {
        this.proportionalVerifiedValue = proportionalVerifiedValue;
    }

    public String getConformityIndicator() {
        return conformityIndicator;
    }

    public void setConformityIndicator(String conformityIndicator) {
        this.conformityIndicator = conformityIndicator;
    }

    public String getReductionIndicator() {
        return reductionIndicator;
    }

    public void setReductionIndicator(String reductionIndicator) {
        this.reductionIndicator = reductionIndicator;
    }

    public String getReferenceValueIndicator() {
        return referenceValueIndicator;
    }

    public void setReferenceValueIndicator(String referenceValueIndicator) {
        this.referenceValueIndicator = referenceValueIndicator;
    }

    public BigDecimal getReferenceValue() {
        return referenceValue;
    }

    public void setReferenceValue(BigDecimal referenceValue) {
        this.referenceValue = referenceValue;
    }

    public String getReferenceValueSituation() {
        return referenceValueSituation;
    }

    public void setReferenceValueSituation(String referenceValueSituation) {
        this.referenceValueSituation = referenceValueSituation;
    }

    public String getValidReferenceValueIndicator() {
        return validReferenceValueIndicator;
    }

    public void setValidReferenceValueIndicator(String validReferenceValueIndicator) {
        this.validReferenceValueIndicator = validReferenceValueIndicator;
    }

    public String getBdbiValueIndicator() {
        return bdbiValueIndicator;
    }

    public void setBdbiValueIndicator(String bdbiValueIndicator) {
        this.bdbiValueIndicator = bdbiValueIndicator;
    }

    public String getBusinessAffectedIndicator() {
        return businessAffectedIndicator;
    }

    public void setBusinessAffectedIndicator(String businessAffectedIndicator) {
        this.businessAffectedIndicator = businessAffectedIndicator;
    }

    public String getReductionAppliedIndicator() {
        return reductionAppliedIndicator;
    }

    public void setReductionAppliedIndicator(String reductionAppliedIndicator) {
        this.reductionAppliedIndicator = reductionAppliedIndicator;
    }
}