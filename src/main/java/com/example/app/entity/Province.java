package com.example.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUCA_PROVINCIA")
public class Province {

    @Id
    @Column(name = "CDPROVINCI")
    private String provinceCode;

    @Column(name = "DSPROVINCI")
    private String provinceDescription;

    @Column(name = "ITACTIVO")
    private String activeIndicator;

    public Province() {
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceDescription() {
        return provinceDescription;
    }

    public void setProvinceDescription(String provinceDescription) {
        this.provinceDescription = provinceDescription;
    }

    public String getActiveIndicator() {
        return activeIndicator;
    }

    public void setActiveIndicator(String activeIndicator) {
        this.activeIndicator = activeIndicator;
    }
}