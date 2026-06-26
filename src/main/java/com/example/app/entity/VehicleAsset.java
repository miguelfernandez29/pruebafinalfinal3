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
@IdClass(VehicleAssetId.class)
public class VehicleAsset {

    @Id
    @Column(name = "AAPRESENTA")
    private Integer presentationYear;

    @Id
    @Column(name = "VFTIPOIMPU")
    private String taxTypeCode;

    @Id
    @Column(name = "CDPRESENTA")
    private String presentationCode;

    @Id
    @Column(name = "CDNIFCAUSA")
    private String causeNif;

    @Id
    @Column(name = "CDSUBCAUSA")
    private String subCauseCode;

    @Id
    @Column(name = "CDSECUBIEN")
    private Integer assetSequence;

    @Column(name = "CDTIPOBIEN")
    private String assetTypeCode;

    @Column(name = "DSTIPOBIEN")
    private String assetTypeDescription;

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

    @Column(name = "DSVEHIMODE")
    private String vehicleModelDescription;

    @Column(name = "TLMATRICUL")
    private String licensePlate;

    @Column(name = "FCMATRICUL")
    private Date registrationDate;

    @Column(name = "FCVEHICATA")
    private Date catalogDate;

    @Column(name = "PTCATALOGO")
    private BigDecimal catalogValue;

    @Column(name = "PCDEPRECIAC")
    private BigDecimal depreciationPercentage;

    @Column(name = "PTDECLARAD")
    private BigDecimal declaredValue;

    @Column(name = "PTCOMPROBA")
    private BigDecimal verifiedValue;

    @Column(name = "PCPARTICIP")
    private BigDecimal participationPercentage;

    public VehicleAsset() {
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

    public String getAssetTypeCode() {
        return assetTypeCode;
    }

    public void setAssetTypeCode(String assetTypeCode) {
        this.assetTypeCode = assetTypeCode;
    }

    public String getAssetTypeDescription() {
        return assetTypeDescription;
    }

    public void setAssetTypeDescription(String assetTypeDescription) {
        this.assetTypeDescription = assetTypeDescription;
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

    public String getVehicleModelDescription() {
        return vehicleModelDescription;
    }

    public void setVehicleModelDescription(String vehicleModelDescription) {
        this.vehicleModelDescription = vehicleModelDescription;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
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

    public BigDecimal getCatalogValue() {
        return catalogValue;
    }

    public void setCatalogValue(BigDecimal catalogValue) {
        this.catalogValue = catalogValue;
    }

    public BigDecimal getDepreciationPercentage() {
        return depreciationPercentage;
    }

    public void setDepreciationPercentage(BigDecimal depreciationPercentage) {
        this.depreciationPercentage = depreciationPercentage;
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

    public BigDecimal getParticipationPercentage() {
        return participationPercentage;
    }

    public void setParticipationPercentage(BigDecimal participationPercentage) {
        this.participationPercentage = participationPercentage;
    }
}