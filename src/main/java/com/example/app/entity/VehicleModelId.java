package com.example.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class VehicleModelId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date catalogDate;
    private String vehicleTypeCode;
    private String vehicleBrandCode;
    private String vehicleModelCode;

    public VehicleModelId() {
    }

    public VehicleModelId(Date catalogDate, String vehicleTypeCode, String vehicleBrandCode, String vehicleModelCode) {
        this.catalogDate = catalogDate;
        this.vehicleTypeCode = vehicleTypeCode;
        this.vehicleBrandCode = vehicleBrandCode;
        this.vehicleModelCode = vehicleModelCode;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleModelId that = (VehicleModelId) o;
        return Objects.equals(catalogDate, that.catalogDate) &&
               Objects.equals(vehicleTypeCode, that.vehicleTypeCode) &&
               Objects.equals(vehicleBrandCode, that.vehicleBrandCode) &&
               Objects.equals(vehicleModelCode, that.vehicleModelCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catalogDate, vehicleTypeCode, vehicleBrandCode, vehicleModelCode);
    }
}
