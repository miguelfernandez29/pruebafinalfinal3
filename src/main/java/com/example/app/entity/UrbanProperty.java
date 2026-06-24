package com.example.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "GATA_BIENURBA")
@IdClass(UrbanPropertyId.class)
public class UrbanProperty {

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

    @Column(name = "CDPROVINCI")
    private String provinceCode;

    @Column(name = "DSPROVINCI")
    private String provinceDescription;

    @Column(name = "CDMUNICIPI")
    private String municipalityCode;

    @Column(name = "DSMUNICIPI")
    private String municipalityDescription;

    @Column(name = "TLCODIPOST")
    private String postalCode;

    @Column(name = "CDTIPOVIAP")
    private String streetTypeCode;

    @Column(name = "TLNOMBREVI")
    private String streetName;

    @Column(name = "TLNUMERO")
    private String streetNumber;

    @Column(name = "TLESCALERA")
    private String stairway;

    @Column(name = "TLPISO")
    private String floor;

    @Column(name = "TLPUERTA")
    private String door;

    @Column(name = "TLREFECATA")
    private String cadastralReference;

    @Column(name = "CDTIPOBIEN")
    private String propertyTypeCode;

    @Column(name = "DSTIPOBIEN")
    private String propertyTypeDescription;

    @Column(name = "NMSUPERFIC")
    private BigDecimal surfaceArea;

    @Column(name = "AACONSTRUC")
    private Integer constructionYear;

    @Column(name = "ITARRENDAM")
    private String rentalIndicator;

    @Column(name = "AACONTARRE")
    private Integer rentalContractYear;

    @Column(name = "ITPROTOFIC")
    private String officialProtectionIndicator;

    @Column(name = "PTMAXVENTA")
    private BigDecimal maxSalePrice;

    @Column(name = "ITDESCALIF")
    private String disqualificationIndicator;

    @Column(name = "ITVIVIHABI")
    private String habitualResidenceIndicator;

    @Column(name = "PTVIVIHABI")
    private BigDecimal habitualResidenceValue;

    @Column(name = "PTDECLARAD")
    private BigDecimal declaredValue;

    @Column(name = "PTCOMPROBA")
    private BigDecimal verifiedValue;

    @Column(name = "PTVALORREF")
    private BigDecimal referenceValue;

    @Column(name = "PTESTIMADO")
    private BigDecimal estimatedValue;

    @Column(name = "ITCONFORME")
    private String conformityIndicator;

    @Column(name = "CDZONAURBA")
    private String urbanZoneCode;

    @Column(name = "CDSECTOR")
    private String sectorCode;

    @Column(name = "CDPAIS")
    private String countryCode;

    public UrbanProperty() {
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