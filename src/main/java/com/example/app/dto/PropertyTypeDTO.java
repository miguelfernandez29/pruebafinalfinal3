package com.example.app.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PropertyTypeDTO {

    @NotNull
    @Size(max = 2)
    private String assetNatureCode;

    @NotNull
    @Size(max = 2)
    private String propertyTypeCode;

    @Size(max = 100)
    private String propertyTypeDescription;

    @Size(max = 1)
    private String activeIndicator;

    public PropertyTypeDTO() {
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

    public String getPropertyTypeDescription() {
        return propertyTypeDescription;
    }

    public void setPropertyTypeDescription(String propertyTypeDescription) {
        this.propertyTypeDescription = propertyTypeDescription;
    }

    public String getActiveIndicator() {
        return activeIndicator;
    }

    public void setActiveIndicator(String activeIndicator) {
        this.activeIndicator = activeIndicator;
    }
}