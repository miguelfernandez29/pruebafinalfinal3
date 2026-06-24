package com.example.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "GATA_SUPACAUS")
@IdClass(SuccessorCauseId.class)
public class SuccessorCause {

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
    @Column(name = "CDNIFSUPAS")
    private String successorNif;

    @Id
    @Column(name = "CDSUBSUPAS")
    private String successorSubsequence;

    @Column(name = "TLNOMBRE")
    private String successorName;

    @Column(name = "PCPARTICIP")
    private BigDecimal participationPercentage;

    public SuccessorCause() {
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

    public String getSuccessorName() {
        return successorName;
    }

    public void setSuccessorName(String successorName) {
        this.successorName = successorName;
    }

    public BigDecimal getParticipationPercentage() {
        return participationPercentage;
    }

    public void setParticipationPercentage(BigDecimal participationPercentage) {
        this.participationPercentage = participationPercentage;
    }
}