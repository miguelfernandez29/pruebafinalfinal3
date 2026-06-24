package com.example.app.entity;

import java.io.Serializable;
import java.util.Objects;

public class LegacyAssetId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer presentationYear;
    private String taxType;
    private String presentationCode;
    private String taxpayerNif;
    private Integer assetSequence;
    private String heirNif;
    private String heirSubsequence;

    public LegacyAssetId() {
    }

    public LegacyAssetId(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, Integer assetSequence, String heirNif, String heirSubsequence) {
        this.presentationYear = presentationYear;
        this.taxType = taxType;
        this.presentationCode = presentationCode;
        this.taxpayerNif = taxpayerNif;
        this.assetSequence = assetSequence;
        this.heirNif = heirNif;
        this.heirSubsequence = heirSubsequence;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LegacyAssetId that = (LegacyAssetId) o;
        return Objects.equals(presentationYear, that.presentationYear) &&
                Objects.equals(taxType, that.taxType) &&
                Objects.equals(presentationCode, that.presentationCode) &&
                Objects.equals(taxpayerNif, that.taxpayerNif) &&
                Objects.equals(assetSequence, that.assetSequence) &&
                Objects.equals(heirNif, that.heirNif) &&
                Objects.equals(heirSubsequence, that.heirSubsequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence, heirNif, heirSubsequence);
    }
}