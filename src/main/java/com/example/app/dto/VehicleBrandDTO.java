package com.example.app.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class VehicleBrandDTO {

    @NotNull
    private Date catalogDate;

    @NotNull
    @Size(max = 2)
    private String vehicleTypeCode;

    @NotNull
    @Size(max = 4)
    private String vehicleBrandCode;

    @Size(max = 50)
    private String vehicleBrandDescription;

    public VehicleBrandDTO() {
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

    public String getVehicleBrandDescription() {
        return vehicleBrandDescription;
    }

    public void setVehicleBrandDescription(String vehicleBrandDescription) {
        this.vehicleBrandDescription = vehicleBrandDescription;
    }
}