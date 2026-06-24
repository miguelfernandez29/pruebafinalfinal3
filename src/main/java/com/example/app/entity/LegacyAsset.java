package com.example.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "GATA_BIENLEGA")
@IdClass(LegacyAssetId.class)
public class LegacyAsset {

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

    @Id
    @Column(name = "CDNIFSUPAS")
    private String heirNif;

    @Id
    @Column(name = "CDSUBSUPAS")
    private String heirSubsequence;

    @Column(name = "ITLEGADO")
    private String legacyIndicator;

    @Column(name = "PCLEGADOSP")
    private BigDecimal legacyPercentage;

    @Column(name = "CDTIPOADQU")
    private String acquisitionTypeCode;

    @Column(name = "TLNOMBRE")
    private String heirName;

    public LegacyAsset() {
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

    public String getHeirNif() {
        return heirNif;
    }

    public void setHeirNif(String heirNif) {
        this.heirNif = heirNif;
    }

    public String getHeirSubsequence() {
        return heirSubsequence;
    }

    public void setHeirSubsequence(String heirSubsequence) {
        this.heirSubsequence = heirSubsequence;
    }

    public String getLegacyIndicator() {
        return legacyIndicator;
    }

    public void setLegacyIndicator(String legacyIndicator) {
        this.legacyIndicator = legacyIndicator;
    }

    public BigDecimal getLegacyPercentage() {
        return legacyPercentage;
    }

    public void setLegacyPercentage(BigDecimal legacyPercentage) {
        this.legacyPercentage = legacyPercentage;
    }

    public String getAcquisitionTypeCode() {
        return acquisitionTypeCode;
    }

    public void setAcquisitionTypeCode(String acquisitionTypeCode) {
        this.acquisitionTypeCode = acquisitionTypeCode;
    }

    public String getHeirName() {
        return heirName;
    }

    public void setHeirName(String heirName) {
        this.heirName = heirName;
    }
}