package com.example.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "GATA_BIENCAUS")
@IdClass(AssetCauseId.class)
public class AssetCause {

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

    @Column(name = "CDNATBIEN2")
    private String assetNatureCode;

    @Column(name = "CDPOSBIEN2")
    private String assetPositionCode;

    @Column(name = "CDTIPOBIEN")
    private String assetTypeCode;

    @Column(name = "DSTIPOBIEN")
    private String assetTypeDescription;

    @Column(name = "PCTRANSMIS")
    private BigDecimal transmissionPercentage;

    @Column(name = "PTDECLARAD")
    private BigDecimal declaredValue;

    @Column(name = "PTCOMPROBA")
    private BigDecimal verifiedValue;

    @Column(name = "PPTCOMPROBA")
    private BigDecimal proportionalVerifiedValue;

    @Column(name = "ITCONFORME")
    private String conformityIndicator;

    @Column(name = "ITRED")
    private String reductionIndicator;

    @Column(name = "ITVALORREF")
    private String referenceValueIndicator;

    @Column(name = "PTVALORREF")
    private BigDecimal referenceValue;

    @Column(name = "CDSITUVREF")
    private String referenceValueSituation;

    @Column(name = "ITVRVALIDO")
    private String validReferenceValueIndicator;

    @Column(name = "ITVALBDBI")
    private String bdbiValueIndicator;

    @Column(name = "ITBIENAFEC")
    private String businessAffectedIndicator;

    @Column(name = "ITREDUCCIO")
    private String reductionAppliedIndicator;

    public AssetCause() {
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