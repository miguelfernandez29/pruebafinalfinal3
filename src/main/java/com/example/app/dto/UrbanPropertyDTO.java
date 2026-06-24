package com.example.app.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class UrbanPropertyDTO {

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

    @Size(max = 10)
    private String stairway;

    @Size(max = 10)
    private String floor;

    @Size(max = 10)
    private String door;

    @Size(max = 20)
    private String cadastralReference;

    @Size(max = 2)
    private String propertyTypeCode;

    @Size(max = 100)
    private String propertyTypeDescription;

    private BigDecimal surfaceArea;

    private Integer constructionYear;

    @Size(max = 1)
    private String rentalIndicator;

    private Integer rentalContractYear;

    @Size(max = 1)
    private String officialProtectionIndicator;

    private BigDecimal maxSalePrice;

    @Size(max = 1)
    private String disqualificationIndicator;

    @Size(max = 1)
    private String habitualResidenceIndicator;

    private BigDecimal habitualResidenceValue;

    private BigDecimal declaredValue;

    private BigDecimal verifiedValue;

    private BigDecimal referenceValue;

    private BigDecimal estimatedValue;

    @Size(max = 1)
    private String conformityIndicator;

    @Size(max = 10)
    private String urbanZoneCode;

    @Size(max = 10)
    private String sectorCode;

    @Size(max = 3)
    private String countryCode;

    public UrbanPropertyDTO() {
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

    public String getStairway() {
        return stairway;
    }

    public void setStairway(String stairway) {
        this.stairway = stairway;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public String getCadastralReference() {
        return cadastralReference;
    }

    public void setCadastralReference(String cadastralReference) {
        this.cadastralReference = cadastralReference;
    }

    public String getPropertyTypeCode() {
        return propertyTypeCode;
    }

    public void setPropertyTypeCode(String propertyTypeCode) {
        this.propertyTypeCode = propertyTypeCode;
    }

    public String getPropertyTypeDescription() {
        return propertyTypeDescription;
    }

    public void setPropertyTypeDescription(String propertyTypeDescription) {
        this.propertyTypeDescription = propertyTypeDescription;
    }

    public BigDecimal getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(BigDecimal surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public Integer getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(Integer constructionYear) {
        this.constructionYear = constructionYear;
    }

    public String getRentalIndicator() {
        return rentalIndicator;
    }

    public void setRentalIndicator(String rentalIndicator) {
        this.rentalIndicator = rentalIndicator;
    }

    public Integer getRentalContractYear() {
        return rentalContractYear;
    }

    public void setRentalContractYear(Integer rentalContractYear) {
        this.rentalContractYear = rentalContractYear;
    }

    public String getOfficialProtectionIndicator() {
        return officialProtectionIndicator;
    }

    public void setOfficialProtectionIndicator(String officialProtectionIndicator) {
        this.officialProtectionIndicator = officialProtectionIndicator;
    }

    public BigDecimal getMaxSalePrice() {
        return maxSalePrice;
    }

    public void setMaxSalePrice(BigDecimal maxSalePrice) {
        this.maxSalePrice = maxSalePrice;
    }

    public String getDisqualificationIndicator() {
        return disqualificationIndicator;
    }

    public void setDisqualificationIndicator(String disqualificationIndicator) {
        this.disqualificationIndicator = disqualificationIndicator;
    }

    public String getHabitualResidenceIndicator() {
        return habitualResidenceIndicator;
    }

    public void setHabitualResidenceIndicator(String habitualResidenceIndicator) {
        this.habitualResidenceIndicator = habitualResidenceIndicator;
    }

    public BigDecimal getHabitualResidenceValue() {
        return habitualResidenceValue;
    }

    public void setHabitualResidenceValue(BigDecimal habitualResidenceValue) {
        this.habitualResidenceValue = habitualResidenceValue;
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

    public BigDecimal getReferenceValue() {
        return referenceValue;
    }

    public void setReferenceValue(BigDecimal referenceValue) {
        this.referenceValue = referenceValue;
    }

    public BigDecimal getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(BigDecimal estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    public String getConformityIndicator() {
        return conformityIndicator;
    }

    public void setConformityIndicator(String conformityIndicator) {
        this.conformityIndicator = conformityIndicator;
    }

    public String getUrbanZoneCode() {
        return urbanZoneCode;
    }

    public void setUrbanZoneCode(String urbanZoneCode) {
        this.urbanZoneCode = urbanZoneCode;
    }

    public String getSectorCode() {
        return sectorCode;
    }

    public void setSectorCode(String sectorCode) {
        this.sectorCode = sectorCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}