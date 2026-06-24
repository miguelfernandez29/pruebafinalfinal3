package com.example.app.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class VehicleTypeDTO {

    @NotNull
    private Date catalogDate;

    @NotNull
    @Size(max = 2)
    private String vehicleTypeCode;

    @Size(max = 50)
    private String vehicleTypeDescription;

    public VehicleTypeDTO() {
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
}