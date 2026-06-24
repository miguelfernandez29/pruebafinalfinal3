package com.example.app.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "VEHICLE_MODEL")
@IdClass(VehicleModelId.class)
public class VehicleModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CATALOG_DATE")
    @Temporal(TemporalType.DATE)
    private Date catalogDate;

    @Id
    @Column(name = "VEHICLE_TYPE_CODE", length = 10)
    private String vehicleTypeCode;

    @Id
    @Column(name = "VEHICLE_BRAND_CODE", length = 10)
    private String vehicleBrandCode;

    @Id
    @Column(name = "VEHICLE_MODEL_CODE", length = 20)
    private String vehicleModelCode;

    @Column(name = "VEHICLE_MODEL_ABBREVIATION", length = 100)
    private String vehicleModelAbbreviation;

    @Column(name = "ENGINE_DISPLACEMENT_CC")
    private Integer engineDisplacementCc;

    @Column(name = "CATALOG_VALUE", precision = 15, scale = 2)
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
