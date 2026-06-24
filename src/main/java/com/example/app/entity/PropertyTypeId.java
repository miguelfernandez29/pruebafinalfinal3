package com.example.app.entity;

import java.io.Serializable;
import java.util.Objects;

public class PropertyTypeId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String assetNatureCode;
    private String propertyTypeCode;

    public PropertyTypeId() {
    }

    public PropertyTypeId(String assetNatureCode, String propertyTypeCode) {
        this.assetNatureCode = assetNatureCode;
        this.propertyTypeCode = propertyTypeCode;
    }

    public String getAssetNatureCode() {
        return assetNatureCode;
    }

    public void setAssetNatureCode(String assetNatureCode) {
        this.assetNatureCode = assetNatureCode;
    }

    public String getPropertyTypeCode() {
        return propertyTypeCode;
    }

    public void setPropertyTypeCode(String propertyTypeCode) {
        this.propertyTypeCode = propertyTypeCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyTypeId that = (PropertyTypeId) o;
        return Objects.equals(assetNatureCode, that.assetNatureCode) &&
                Objects.equals(propertyTypeCode, that.propertyTypeCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assetNatureCode, propertyTypeCode);
    }
}