package com.example.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "GATA_INTERESA")
@IdClass(InterestedPartyId.class)
public class InterestedParty {

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
    @Column(name = "CDNIFINTER")
    private String partyNif;

    @Id
    @Column(name = "CDSUBINTER")
    private String partySubsequence;

    @Column(name = "TLNOMBRE")
    private String partyName;

    @Column(name = "CDTIPOINTE")
    private String partyTypeCode;

    public InterestedParty() {
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

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getPartyTypeCode() {
        return partyTypeCode;
    }

    public void setPartyTypeCode(String partyTypeCode) {
        this.partyTypeCode = partyTypeCode;
    }
}