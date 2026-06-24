package com.example.app.entity;

import java.io.Serializable;
import java.util.Objects;

public class ListedSecuritiesId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer presentationYear;
    private String taxType;
    private String presentationCode;
    private String taxpayerNif;
    private Integer assetSequence;

    public ListedSecuritiesId() {
    }

    public ListedSecuritiesId(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, Integer assetSequence) {
        this.presentationYear = presentationYear;
        this.taxType = taxType;
        this.presentationCode = presentationCode;
        this.taxpayerNif = taxpayerNif;
        this.assetSequence = assetSequence;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListedSecuritiesId that = (ListedSecuritiesId) o;
        return Objects.equals(presentationYear, that.presentationYear) &&
                Objects.equals(taxType, that.taxType) &&
                Objects.equals(presentationCode, that.presentationCode) &&
                Objects.equals(taxpayerNif, that.taxpayerNif) &&
                Objects.equals(assetSequence, that.assetSequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
    }
}