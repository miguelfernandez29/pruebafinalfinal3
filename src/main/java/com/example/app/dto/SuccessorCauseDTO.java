package com.example.app.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class SuccessorCauseDTO {

    @NotNull
    private Integer presentationYear;

    @NotNull
    @Size(max = 2)
    private String taxType;

    @NotNull
    @Size(max = 14)
    private String presentationCode;

    @NotNull
    @Size(max = 9)
    private String taxpayerNif;

    @NotNull
    @Size(max = 9)
    private String successorNif;

    @NotNull
    @Size(max = 2)
    private String successorSubsequence;

    @Size(max = 100)
    private String successorName;

    private BigDecimal participationPercentage;

    public SuccessorCauseDTO() {
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