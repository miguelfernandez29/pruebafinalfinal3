package com.example.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "GATA_VEHITIPO")
@IdClass(VehicleTypeId.class)
public class VehicleType {

    @Id
    @Column(name = "FCVEHICATA")
    private Date catalogDate;

    @Id
    @Column(name = "CDVEHITIPO")
    private String vehicleTypeCode;

    @Column(name = "DSVEHITIPO")
    private String vehicleTypeDescription;

    public VehicleType() {
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