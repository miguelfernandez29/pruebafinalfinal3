package com.example.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "GATA_BIENCAUS")
@IdClass(AssetCauseId.class)
public class AssetCause {

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

    @Column(name = "CDNATBIEN2")
    private String assetNatureCode;

    @Column(name = "DSNATBIEN2")
    private String assetNatureDescription;

    @Column(name = "CDPOSBIEN2")
    private String assetPositionCode;

    @Column(name = "DSPOSBIEN2")
    private String assetPositionDescription;

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

    @Column(name = "CDSITBIHE2")
    private String assetSituationCode;

    @Column(name = "FCDEVENGO")
    private Date accrualDate;

    public AssetCause() {
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

    public String getAssetNatureCode() {
        return assetNatureCode;
    }

    public void setAssetNatureCode(String assetNatureCode) {
        this.assetNatureCode = assetNatureCode;
    }

    public String getAssetNatureDescription() {
        return assetNatureDescription;
    }

    public void setAssetNatureDescription(String assetNatureDescription) {
        this.assetNatureDescription = assetNatureDescription;
    }

    public String getAssetPositionCode() {
        return assetPositionCode;
    }

    public void setAssetPositionCode(String assetPositionCode) {
        this.assetPositionCode = assetPositionCode;
    }

    public String getAssetPositionDescription() {
        return assetPositionDescription;
    }

    public void setAssetPositionDescription(String assetPositionDescription) {
        this.assetPositionDescription = assetPositionDescription;
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

    public String getAssetSituationCode() {
        return assetSituationCode;
    }

    public void setAssetSituationCode(String assetSituationCode) {
        this.assetSituationCode = assetSituationCode;
    }

    public Date getAccrualDate() {
        return accrualDate;
    }

    public void setAccrualDate(Date accrualDate) {
        this.accrualDate = accrualDate;
    }
}