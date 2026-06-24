package com.example.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "GATA_BIENVEHI")
@IdClass(VehicleId.class)
public class Vehicle {

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

    @Column(name = "FCMATRICUL")
    private Date registrationDate;

    @Column(name = "FCVEHICATA")
    private Date catalogDate;

    @Column(name = "CDVEHITIPO")
    private String vehicleTypeCode;

    @Column(name = "DSVEHITIPO")
    private String vehicleTypeDescription;

    @Column(name = "CDVEHIMARC")
    private String vehicleBrandCode;

    @Column(name = "DSVEHIMARC")
    private String vehicleBrandDescription;

    @Column(name = "CDVEHIMODE")
    private String vehicleModelCode;

    @Column(name = "DSABREV_MODELO")
    private String vehicleModelAbbreviation;

    @Column(name = "NMCILINDCC")
    private Integer engineDisplacementCc;

    @Column(name = "PTCATALOGO")
    private BigDecimal catalogValue;

    @Column(name = "PTDECLARAD")
    private BigDecimal declaredValue;

    @Column(name = "PTCOMPROBA")
    private BigDecimal verifiedValue;

    @Column(name = "ITCONFORME")
    private String conformityIndicator;

    public Vehicle() {
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getCatalogDate() {
        return catalogDate;
    }

    public void setCatalogDate(Date catalogDate) {
        this.catalogDate = catalogDate;
    }

    public String getVehicleTypeCode() {
        return vehicleTypeCode;
    }

    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }

    public String getVehicleTypeDescription() {
        return vehicleTypeDescription;
    }

    public void setVehicleTypeDescription(String vehicleTypeDescription) {
        this.vehicleTypeDescription = vehicleTypeDescription;
    }

    public String getVehicleBrandCode() {
        return vehicleBrandCode;
    }

    public void setVehicleBrandCode(String vehicleBrandCode) {
        this.vehicleBrandCode = vehicleBrandCode;
    }

    public String getVehicleBrandDescription() {
        return vehicleBrandDescription;
    }

    public void setVehicleBrandDescription(String vehicleBrandDescription) {
        this.vehicleBrandDescription = vehicleBrandDescription;
    }

    public String getVehicleModelCode() {
        return vehicleModelCode;
    }

    public void setVehicleModelCode(String vehicleModelCode) {
        this.vehicleModelCode = vehicleModelCode;
    }

    public String getVehicleModelAbbreviation() {
        return vehicleModelAbbreviation;
    }

    public void setVehicleModelAbbreviation(String vehicleModelAbbreviation) {
        this.vehicleModelAbbreviation = vehicleModelAbbreviation;
    }

    public Integer getEngineDisplacementCc() {
        return engineDisplacementCc;
    }

    public void setEngineDisplacementCc(Integer engineDisplacementCc) {
        this.engineDisplacementCc = engineDisplacementCc;
    }

    public BigDecimal getCatalogValue() {
        return catalogValue;
    }

    public void setCatalogValue(BigDecimal catalogValue) {
        this.catalogValue = catalogValue;
    }

    public BigDecimal getDeclaredValue() {
        return declaredValue;
    }

    public void setDeclaredValue(BigDecimal declaredValue) {
        this.declaredValue = declaredValue;
    }

    public BigDecimal getVerifiedValue() {
        return verifiedValue;
    }

    public void setVerifiedValue(BigDecimal verifiedValue) {
        this.verifiedValue = verifiedValue;
    }

    public String getConformityIndicator() {
        return conformityIndicator;
    }

    public void setConformityIndicator(String conformityIndicator) {
        this.conformityIndicator = conformityIndicator;
    }
}