package com.example.app.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

public class VehicleModelDTO {

    @NotNull
    private Date catalogDate;

    @NotNull
    @Size(max = 2)
    private String vehicleTypeCode;

    @NotNull
    @Size(max = 4)
    private String vehicleBrandCode;

    @NotNull
    @Size(max = 10)
    private String vehicleModelCode;

    @Size(max = 100)
    private String vehicleModelAbbreviation;

    @Size(max = 200)
    private String vehicleModelDescription;

    private Integer engineDisplacementCc;

    private BigDecimal catalogValue;

    public VehicleModelDTO() {
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

    public String getVehicleBrandCode() {
        return vehicleBrandCode;
    }

    public void setVehicleBrandCode(String vehicleBrandCode) {
        this.vehicleBrandCode = vehicleBrandCode;
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
}