package com.example.app.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class BusinessAssetDTO {

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

    @Size(max = 10)
    private String activityCode;

    @Size(max = 200)
    private String activityDescription;

    @Size(max = 10)
    private String epigraphCode;

    @Size(max = 200)
    private String epigraphDescription;

    @Size(max = 500)
    private String assetDescription;

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

    @Size(max = 5)
    private String streetTypeCode;

    @Size(max = 100)
    private String streetName;

    @Size(max = 10)
    private String streetNumber;

    @Size(max = 1)
    private String reductionIndicator;

    @Size(max = 1)
    private String affectationIndicator;

    private BigDecimal declaredValue;

    private BigDecimal verifiedValue;

    @Size(max = 1)
    private String conformityIndicator;

    public BusinessAssetDTO() {
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

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public String getEpigraphCode() {
        return epigraphCode;
    }

    public void setEpigraphCode(String epigraphCode) {
        this.epigraphCode = epigraphCode;
    }

    public String getEpigraphDescription() {
        return epigraphDescription;
    }

    public void setEpigraphDescription(String epigraphDescription) {
        this.epigraphDescription = epigraphDescription;
    }

    public String getAssetDescription() {
        return assetDescription;
    }

    public void setAssetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
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

    public String getReductionIndicator() {
        return reductionIndicator;
    }

    public void setReductionIndicator(String reductionIndicator) {
        this.reductionIndicator = reductionIndicator;
    }

    public String getAffectationIndicator() {
        return affectationIndicator;
    }

    public void setAffectationIndicator(String affectationIndicator) {
        this.affectationIndicator = affectationIndicator;
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