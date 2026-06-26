package com.example.app.entity;

import java.io.Serializable;
import java.util.Objects;

public class OtherAssetId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer presentationYear;
    private String taxTypeCode;
    private String presentationCode;
    private String causeNif;
    private String subCauseCode;
    private Integer assetSequence;

    public OtherAssetId() {
    }

    public OtherAssetId(Integer presentationYear, String taxTypeCode, String presentationCode,
                        String causeNif, String subCauseCode, Integer assetSequence) {
        this.presentationYear = presentationYear;
        this.taxTypeCode = taxTypeCode;
        this.presentationCode = presentationCode;
        this.causeNif = causeNif;
        this.subCauseCode = subCauseCode;
        this.assetSequence = assetSequence;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OtherAssetId that = (OtherAssetId) o;
        return Objects.equals(presentationYear, that.presentationYear) &&
               Objects.equals(taxTypeCode, that.taxTypeCode) &&
               Objects.equals(presentationCode, that.presentationCode) &&
               Objects.equals(causeNif, that.causeNif) &&
               Objects.equals(subCauseCode, that.subCauseCode) &&
               Objects.equals(assetSequence, that.assetSequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(presentationYear, taxTypeCode, presentationCode, causeNif, subCauseCode, assetSequence);
    }
}