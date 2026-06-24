package com.example.app.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProvinceDTO {

    @NotNull
    @Size(max = 2)
    private String provinceCode;

    @Size(max = 50)
    private String provinceDescription;

    @Size(max = 1)
    private String activeIndicator;

    public ProvinceDTO() {
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