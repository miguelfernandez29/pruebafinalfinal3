package com.example.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "GATA_TIPOBIEN")
@IdClass(PropertyTypeId.class)
public class PropertyType {

    @Id
    @Column(name = "CDNATBIEN2")
    private String assetNatureCode;

    @Id
    @Column(name = "CDTIPOBIEN")
    private String propertyTypeCode;

    @Column(name = "DSTIPOBIEN")
    private String propertyTypeDescription;

    @Column(name = "ITACTIVO")
    private String activeIndicator;

    public PropertyType() {
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