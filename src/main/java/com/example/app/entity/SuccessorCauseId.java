package com.example.app.entity;

import java.io.Serializable;
import java.util.Objects;

public class SuccessorCauseId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer presentationYear;
    private String taxType;
    private String presentationCode;
    private String taxpayerNif;
    private String successorNif;
    private String successorSubsequence;

    public SuccessorCauseId() {
    }

    public SuccessorCauseId(Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, String successorNif, String successorSubsequence) {
        this.presentationYear = presentationYear;
        this.taxType = taxType;
        this.presentationCode = presentationCode;
        this.taxpayerNif = taxpayerNif;
        this.successorNif = successorNif;
        this.successorSubsequence = successorSubsequence;
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

    public String getSuccessorNif() {
        return successorNif;
    }

    public void setSuccessorNif(String successorNif) {
        this.successorNif = successorNif;
    }

    public String getSuccessorSubsequence() {
        return successorSubsequence;
    }

    public void setSuccessorSubsequence(String successorSubsequence) {
        this.successorSubsequence = successorSubsequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuccessorCauseId that = (SuccessorCauseId) o;
        return Objects.equals(presentationYear, that.presentationYear) &&
                Objects.equals(taxType, that.taxType) &&
                Objects.equals(presentationCode, that.presentationCode) &&
                Objects.equals(taxpayerNif, that.taxpayerNif) &&
                Objects.equals(successorNif, that.successorNif) &&
                Objects.equals(successorSubsequence, that.successorSubsequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(presentationYear, taxType, presentationCode, taxpayerNif, successorNif, successorSubsequence);
    }
}