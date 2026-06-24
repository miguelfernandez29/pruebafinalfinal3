package com.example.app.entity;

import java.io.Serializable;
import java.util.Objects;

public class InterestedPartyId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer presentationYear;
    private String taxType;
    private String presentationCode;
    private String taxpayerNif;
    private String partyNif;
    private String partySubsequence;

    public InterestedPartyId() {
    }

    public InterestedPartyId(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, String partyNif, String partySubsequence) {
        this.presentationYear = presentationYear;
        this.taxType = taxType;
        this.presentationCode = presentationCode;
        this.taxpayerNif = taxpayerNif;
        this.partyNif = partyNif;
        this.partySubsequence = partySubsequence;
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

    public String getPartyNif() {
        return partyNif;
    }

    public void setPartyNif(String partyNif) {
        this.partyNif = partyNif;
    }

    public String getPartySubsequence() {
        return partySubsequence;
    }

    public void setPartySubsequence(String partySubsequence) {
        this.partySubsequence = partySubsequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InterestedPartyId that = (InterestedPartyId) o;
        return Objects.equals(presentationYear, that.presentationYear) &&
                Objects.equals(taxType, that.taxType) &&
                Objects.equals(presentationCode, that.presentationCode) &&
                Objects.equals(taxpayerNif, that.taxpayerNif) &&
                Objects.equals(partyNif, that.partyNif) &&
                Objects.equals(partySubsequence, that.partySubsequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(presentationYear, taxType, presentationCode, taxpayerNif, partyNif, partySubsequence);
    }
}