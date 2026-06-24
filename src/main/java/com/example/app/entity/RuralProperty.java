package com.example.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "GATA_BIENRUST")
@IdClass(RuralPropertyId.class)
public class RuralProperty {

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

    @Column(name = "TLREFECATA")
    private String cadastralReference;

    @Column(name = "CDTIPOBIEN")
    private String propertyTypeCode;

    @Column(name = "DSTIPOBIEN")
    private String propertyTypeDescription;

    @Column(name = "CDUNIDMEDI")
    private String measurementUnitCode;

    @Column(name = "NMSUPERFIC")
    private BigDecimal surfaceArea;

    @Column(name = "TLPARAJE")
    private String locationName;

    @Column(name = "TLPOLIGONO")
    private String polygon;

    @Column(name = "TLPARCELA")
    private String parcel;

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

    public RuralProperty() {
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

    public String getMeasurementUnitCode() {
        return measurementUnitCode;
    }

    public void setMeasurementUnitCode(String measurementUnitCode) {
        this.measurementUnitCode = measurementUnitCode;
    }

    public BigDecimal getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(BigDecimal surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getPolygon() {
        return polygon;
    }

    public void setPolygon(String polygon) {
        this.polygon = polygon;
    }

    public String getParcel() {
        return parcel;
    }

    public void setParcel(String parcel) {
        this.parcel = parcel;
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
}