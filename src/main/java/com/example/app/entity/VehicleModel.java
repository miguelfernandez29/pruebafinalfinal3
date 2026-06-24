package com.example.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "GATA_VEHIMODE")
@IdClass(VehicleModelId.class)
public class VehicleModel {

    @Id
    @Column(name = "FCVEHICATA")
    private Date catalogDate;

    @Id
    @Column(name = "CDVEHITIPO")
    private String vehicleTypeCode;

    @Id
    @Column(name = "CDVEHIMARC")
    private String vehicleBrandCode;

    @Id
    @Column(name = "CDVEHIMODE")
    private String vehicleModelCode;

    @Column(name = "DSABREV_MODELO")
    private String vehicleModelAbbreviation;

    @Column(name = "DSVEHIMODE")
    private String vehicleModelDescription;

    @Column(name = "NMCILINDCC")
    private Integer engineDisplacementCc;

    @Column(name = "PTCATALOGO")
    private BigDecimal catalogValue;

    public VehicleModel() {
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