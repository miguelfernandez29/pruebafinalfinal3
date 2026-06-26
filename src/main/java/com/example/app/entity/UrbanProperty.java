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
    private String taxTypeCode;

    @Id
    @Column(name = "CDPRESENTA")
    private String presentationCode;

    @Id
    @Column(name = "CDNIFCAUSA")
    private String causeNif;

    @Id
    @Column(name = "CDSUBCAUSA")
    private String subCauseCode;

    @Id
    @Column(name = "CDSECUBIEN")
    private Integer assetSequence;

    @Column(name = "CDTIPOBIEN")
    private String propertyTypeCode;

    @Column(name = "DSTIPOBIEN")
    private String propertyTypeDescription;

    @Column(name = "TLREFECATA")
    private String cadastralReference;

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

    @Column(name = "CDTIPOVIAL")
    private String streetTypeCode;

    @Column(name = "DSTIPOVIAL")
    private String streetTypeDescription;

    @Column(name = "TLNOMBVIAL")
    private String streetName;

    @Column(name = "TLNUMEVIAL")
    private String streetNumber;

    @Column(name = "TLESCALERA")
    private String stairway;

    @Column(name = "TLPISO")
    private String floor;

    @Column(name = "TLPUERTA")
    private String door;

    @Column(name = "AACONSTRUC")
    private Integer constructionYear;

    @Column(name = "NUSUPCONST")
    private BigDecimal constructedSurface;

    @Column(name = "NUSUPSUELO")
    private BigDecimal landSurface;

    @Column(name = "ITARRENDAM")
    private String rentalIndicator;

    @Column(name = "AACONTARRE")
    private Integer rentalContractYear;

    @Column(name = "ITPROTOFIC")
    private String officialProtectionIndicator;

    @Column(name = "PTMAXVENTA")
    private BigDecimal maxSalePrice;

    @Column(name = "PTDECLARAD")
    private BigDecimal declaredValue;

    @Column(name = "PTCOMPROBA")
    private BigDecimal verifiedValue;

    @Column(name = "PTVALORREF")
    private BigDecimal referenceValue;

    @Column(name = "ITCONFORME")
    private String conformityIndicator;

    @Column(name = "CDSITUVREF")
    private String referenceValueSituation;

    @Column(name = "ITVALORREF")
    private String referenceValueIndicator;

    @Column(name = "ITVRVALIDO")
    private String validReferenceValueIndicator;

    @Column(name = "ITVALBDBI")
    private String bdbiValueIndicator;

    @Column(name = "CDZONAURBA")
    private String urbanZoneCode;

    @Column(name = "CDSECTOR")
    private String sectorCode;

    @Column(name = "PTVALORCAS")
    private BigDecimal cadastralValue;

    @Column(name = "PCPARTICIP")
    private BigDecimal participationPercentage;

    public UrbanProperty() {
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