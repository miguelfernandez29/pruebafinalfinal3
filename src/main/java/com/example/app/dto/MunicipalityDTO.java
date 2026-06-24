package com.example.app.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MunicipalityDTO {

    @NotNull
    @Size(max = 2)
    private String provinceCode;

    @NotNull
    @Size(max = 3)
    private String municipalityCode;

    @Size(max = 100)
    private String municipalityDescription;

    @Size(max = 1)
    private String activeIndicator;

    public MunicipalityDTO() {
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getMunicipalityCode() {
        return municipalityCode;
    }

    public void setMunicipalityCode(String municipalityCode) {
        this.municipalityCode = municipalityCode;
    }

    public String getMunicipalityDescription() {
        return municipalityDescription;
    }

    public void setMunicipalityDescription(String municipalityDescription) {
        this.municipalityDescription = municipalityDescription;
    }

    public String getActiveIndicator() {
        return activeIndicator;
    }

    public void setActiveIndicator(String activeIndicator) {
        this.activeIndicator = activeIndicator;
    }
}