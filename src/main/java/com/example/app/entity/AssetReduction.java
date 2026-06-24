package com.example.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "GATA_BIENRECA")
@IdClass(AssetReductionId.class)
public class AssetReduction {

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
    @Column(name = "CDREDUCCION")
    private String reductionCode;

    @Column(name = "DSREDUCCION")
    private String reductionDescription;

    @Column(name = "PCREDUCCION")
    private BigDecimal reductionPercentage;

    @Column(name = "PTREDUCCION")
    private BigDecimal reductionAmount;

    public AssetReduction() {
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

    public String getReductionCode() {
        return reductionCode;
    }

    public void setReductionCode(String reductionCode) {
        this.reductionCode = reductionCode;
    }

    public String getReductionDescription() {
        return reductionDescription;
    }

    public void setReductionDescription(String reductionDescription) {
        this.reductionDescription = reductionDescription;
    }

    public BigDecimal getReductionPercentage() {
        return reductionPercentage;
    }

    public void setReductionPercentage(BigDecimal reductionPercentage) {
        this.reductionPercentage = reductionPercentage;
    }

    public BigDecimal getReductionAmount() {
        return reductionAmount;
    }

    public void setReductionAmount(BigDecimal reductionAmount) {
        this.reductionAmount = reductionAmount;
    }
}