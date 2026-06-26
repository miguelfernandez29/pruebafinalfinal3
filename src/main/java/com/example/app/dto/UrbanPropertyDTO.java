package com.example.app.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class UrbanPropertyDto {

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
    private String propertyTypeCode;

    @Size(max = 100)
    private String propertyTypeDescription;

    @Size(max = 20)
    private String cadastralReference;

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

    @Size(max = 10)
    private String stairway;

    @Size(max = 10)
    private String floor;

    @Size(max = 10)
    private String door;

    private Integer constructionYear;

    private BigDecimal constructedSurface;

    private BigDecimal landSurface;

    @Size(max = 1)
    private String rentalIndicator;

    private Integer rentalContractYear;

    @Size(max = 1)
    private String officialProtectionIndicator;

    private BigDecimal maxSalePrice;

    private BigDecimal declaredValue;

    private BigDecimal verifiedValue;

    private BigDecimal referenceValue;

    @Size(max = 1)
    private String conformityIndicator;

    @Size(max = 2)
    private String referenceValueSituation;

    @Size(max = 1)
    private String referenceValueIndicator;

    @Size(max = 1)
    private String validReferenceValueIndicator;

    @Size(max = 1)
    private String bdbiValueIndicator;

    @Size(max = 10)
    private String urbanZoneCode;

    @Size(max = 10)
    private String sectorCode;

    private BigDecimal cadastralValue;

    private BigDecimal participationPercentage;

    public UrbanPropertyDto() {
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

    public String getCadastralReference() {
        return cadastralReference;
    }

    public void setCadastralReference(String cadastralReference) {
        this.cadastralReference = cadastralReference;
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

    public Integer getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(Integer constructionYear) {
        this.constructionYear = constructionYear;
    }

    public BigDecimal getConstructedSurface() {
        return constructedSurface;
    }

    public void setConstructedSurface(BigDecimal constructedSurface) {
        this.constructedSurface = constructedSurface;
    }

    public BigDecimal getLandSurface() {
        return landSurface;
    }

    public void setLandSurface(BigDecimal landSurface) {
        this.landSurface = landSurface;
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

    public String getConformityIndicator() {
        return conformityIndicator;
    }

    public void setConformityIndicator(String conformityIndicator) {
        this.conformityIndicator = conformityIndicator;
    }

    public String getReferenceValueSituation() {
        return referenceValueSituation;
    }

    public void setReferenceValueSituation(String referenceValueSituation) {
        this.referenceValueSituation = referenceValueSituation;
    }

    public String getReferenceValueIndicator() {
        return referenceValueIndicator;
    }

    public void setReferenceValueIndicator(String referenceValueIndicator) {
        this.referenceValueIndicator = referenceValueIndicator;
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

    public BigDecimal getCadastralValue() {
        return cadastralValue;
    }

    public void setCadastralValue(BigDecimal cadastralValue) {
        this.cadastralValue = cadastralValue;
    }

    public BigDecimal getParticipationPercentage() {
        return participationPercentage;
    }

    public void setParticipationPercentage(BigDecimal participationPercentage) {
        this.participationPercentage = participationPercentage;
    }
}