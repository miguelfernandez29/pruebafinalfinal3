package com.example.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "GATA_DATOGENA")
@IdClass(GeneralDataId.class)
public class GeneralData {

    @Id
    @Column(name = "CDTIPODATO")
    private String dataType;

    @Id
    @Column(name = "CDCODIGO")
    private String dataCode;

    @Column(name = "DSDESCRIPC")
    private String description;

    @Column(name = "ITACTIVO")
    private String activeIndicator;

    public GeneralData() {
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActiveIndicator() {
        return activeIndicator;
    }

    public void setActiveIndicator(String activeIndicator) {
        this.activeIndicator = activeIndicator;
    }
}