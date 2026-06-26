package com.example.app.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class BusinessAssetDto {

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

    @Size(max = 3)
    private String assetTypeCode;

    @Size(max = 100)
    private String assetTypeDescription;

    @Size(max = 9)
    private String companyNif;

    @Size(max = 100)
    private String companyName;

    @Size(max = 2)
    private String provinceCode;

    @Size(max = 50)
    private String provinceDescription;

    @Size(max = 3)
    private String municipalityCode;

    @Size(max = 100)
    private String municipalityDescription;

    @Size(max = 5)
    private String postalCode;

    @Size(max = 2)
    private String streetTypeCode;

    @Size(max = 20)
    private String streetTypeDescription;

    @Size(max = 100)
    private String streetName;

    @Size(max = 10)
    private String streetNumber;

    @Size(max = 500)
    private String description;

    @Size(max = 1)
    private String businessAffectedIndicator;

    @Size(max = 1)
    private String reductionIndicator;

    private BigDecimal declaredValue;

    private BigDecimal verifiedValue;

    private BigDecimal participationPercentage;

    public BusinessAssetDto() {
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

    public String getCompanyNif() {
        return companyNif;
    }

    public void setCompanyNif(String companyNif) {
        this.companyNif = companyNif;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceDescription() {
        return provinceDescription;
    }

    public void setProvinceDescription(String provinceDescription) {
        this.provinceDescription = provinceDescription;
    }

    public String getMunicipalityCode() {
        return municipalityCode;
    }

    public void setMunicipalityCode(String municipalityCode) {
        this.municipalityCode = municipalityCode;
    }

    public String getMunicipalityDescription() {
        return municipalityDescription;
    }

    public void setMunicipalityDescription(String municipalityDescription) {
        this.municipalityDescription = municipalityDescription;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreetTypeCode() {
        return streetTypeCode;
    }

    public void setStreetTypeCode(String streetTypeCode) {
        this.streetTypeCode = streetTypeCode;
    }

    public String getStreetTypeDescription() {
        return streetTypeDescription;
    }

    public void setStreetTypeDescription(String streetTypeDescription) {
        this.streetTypeDescription = streetTypeDescription;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBusinessAffectedIndicator() {
        return businessAffectedIndicator;
    }

    public void setBusinessAffectedIndicator(String businessAffectedIndicator) {
        this.businessAffectedIndicator = businessAffectedIndicator;
    }

    public String getReductionIndicator() {
        return reductionIndicator;
    }

    public void setReductionIndicator(String reductionIndicator) {
        this.reductionIndicator = reductionIndicator;
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

    public BigDecimal getParticipationPercentage() {
        return participationPercentage;
    }

    public void setParticipationPercentage(BigDecimal participationPercentage) {
        this.participationPercentage = participationPercentage;
    }
}