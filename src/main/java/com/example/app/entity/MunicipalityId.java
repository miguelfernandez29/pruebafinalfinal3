package com.example.app.entity;

import java.io.Serializable;
import java.util.Objects;

public class MunicipalityId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String provinceCode;
    private String municipalityCode;

    public MunicipalityId() {
    }

    public MunicipalityId(String provinceCode, String municipalityCode) {
        this.provinceCode = provinceCode;
        this.municipalityCode = municipalityCode;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MunicipalityId that = (MunicipalityId) o;
        return Objects.equals(provinceCode, that.provinceCode) &&
                Objects.equals(municipalityCode, that.municipalityCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(provinceCode, municipalityCode);
    }
}