package com.example.app.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Date;

public class VehicleBrandId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date catalogDate;
    private String vehicleTypeCode;
    private String vehicleBrandCode;

    public VehicleBrandId() {
    }

    public VehicleBrandId(Date catalogDate, String vehicleTypeCode, String vehicleBrandCode) {
        this.catalogDate = catalogDate;
        this.vehicleTypeCode = vehicleTypeCode;
        this.vehicleBrandCode = vehicleBrandCode;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleBrandId that = (VehicleBrandId) o;
        return Objects.equals(catalogDate, that.catalogDate) &&
                Objects.equals(vehicleTypeCode, that.vehicleTypeCode) &&
                Objects.equals(vehicleBrandCode, that.vehicleBrandCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catalogDate, vehicleTypeCode, vehicleBrandCode);
    }
}