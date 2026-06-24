package com.example.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "GATA_VEHIMARC")
@IdClass(VehicleBrandId.class)
public class VehicleBrand {

    @Id
    @Column(name = "FCVEHICATA")
    private Date catalogDate;

    @Id
    @Column(name = "CDVEHITIPO")
    private String vehicleTypeCode;

    @Id
    @Column(name = "CDVEHIMARC")
    private String vehicleBrandCode;

    @Column(name = "DSVEHIMARC")
    private String vehicleBrandDescription;

    public VehicleBrand() {
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