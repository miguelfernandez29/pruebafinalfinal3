package com.example.app.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

public class VehicleAssetDto {

    @NotNull
    private Integer presentationYear;

    @NotNull
    @Size(max = 2)
    private String taxTypeCode;

    @NotNull
    @Size(max = 14)
    private String presentationCode;

    @NotNull
    @Size(max = 9)
    private String causeNif;

    @NotNull
    @Size(max = 2)
    private String subCauseCode;

    @NotNull
    private Integer assetSequence;

    @Size(max = 3)
    private String assetTypeCode;

    @Size(max = 100)
    private String assetTypeDescription;

    @Size(max = 2)
    private String vehicleTypeCode;

    @Size(max = 50)
    private String vehicleTypeDescription;

    @Size(max = 4)
    private String vehicleBrandCode;

    @Size(max = 50)
    private String vehicleBrandDescription;

    @Size(max = 10)
    private String vehicleModelCode;

    @Size(max = 50)
    private String vehicleModelAbbreviation;

    @Size(max = 100)
    private String vehicleModelDescription;

    @Size(max = 10)
    private String licensePlate;

    private Date registrationDate;

    private Date catalogDate;

    private BigDecimal catalogValue;

    private BigDecimal depreciationPercentage;

    private BigDecimal declaredValue;

    private BigDecimal verifiedValue;

    private BigDecimal participationPercentage;

    public VehicleAssetDto() {
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