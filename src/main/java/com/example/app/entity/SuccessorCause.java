package com.example.app.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SUCCESSOR_CAUSE")
@IdClass(SuccessorCauseId.class)
public class SuccessorCause implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PRESENTATION_YEAR")
    private Integer presentationYear;

    @Id
    @Column(name = "TAX_TYPE", length = 2)
    private String taxType;

    @Id
    @Column(name = "PRESENTATION_CODE", length = 20)
    private String presentationCode;

    @Id
    @Column(name = "TAXPAYER_NIF", length = 15)
    private String taxpayerNif;

    @Id
    @Column(name = "HEIR_NIF", length = 15)
    private String heirNif;

    @Id
    @Column(name = "HEIR_SUBSEQUENCE", length = 5)
    private String heirSubsequence;

    @Column(name = "HEIR_NAME", length = 100)
    private String heirName;

    public SuccessorCause() {}

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

    public String getHeirName() {
        return heirName;
    }

    public void setHeirName(String heirName) {
        this.heirName = heirName;
    }
}
