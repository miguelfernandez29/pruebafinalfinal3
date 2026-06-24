package com.example.app.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "VEHICLE_CATALOG")
public class VehicleCatalog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CATALOG_DATE")
    @Temporal(TemporalType.DATE)
    private Date catalogDate;

    @Column(name = "DESCRIPTION", length = 200)
    private String description;

    public VehicleCatalog() {}

    public Date getCatalogDate() {
        return catalogDate;
    }

    public void setCatalogDate(Date catalogDate) {
        this.catalogDate = catalogDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
