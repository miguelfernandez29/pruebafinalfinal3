package com.example.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUCA_TIPO_VIAL")
public class StreetType {

    @Id
    @Column(name = "CDTIPOVIAP")
    private String streetTypeCode;

    @Column(name = "DSTIPOVIAP")
    private String streetTypeDescription;

    @Column(name = "ITACTIVO")
    private String activeIndicator;

    public StreetType() {
    }

    public String getStreetTypeCode() {
        return streetTypeCode;
    }

    public void setStreetTypeCode(String streetTypeCode) {
        this.streetTypeCode = streetTypeCode;
    }

    public String getStreetTypeDescription() {
        return streetTypeDescription;
    }

    public void setStreetTypeDescription(String streetTypeDescription) {
        this.streetTypeDescription = streetTypeDescription;
    }

    public String getActiveIndicator() {
        return activeIndicator;
    }

    public void setActiveIndicator(String activeIndicator) {
        this.activeIndicator = activeIndicator;
    }
}