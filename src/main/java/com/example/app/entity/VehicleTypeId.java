package com.example.app.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Date;

public class VehicleTypeId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date catalogDate;
    private String vehicleTypeCode;

    public VehicleTypeId() {
    }

    public VehicleTypeId(Date catalogDate, String vehicleTypeCode) {
        this.catalogDate = catalogDate;
        this.vehicleTypeCode = vehicleTypeCode;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleTypeId that = (VehicleTypeId) o;
        return Objects.equals(catalogDate, that.catalogDate) &&
                Objects.equals(vehicleTypeCode, that.vehicleTypeCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catalogDate, vehicleTypeCode);
    }
}