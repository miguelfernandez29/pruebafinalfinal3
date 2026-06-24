package com.example.app.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class GeneralDataDTO {

    @NotNull
    @Size(max = 10)
    private String dataType;

    @NotNull
    @Size(max = 10)
    private String dataCode;

    @Size(max = 200)
    private String description;

    @Size(max = 1)
    private String activeIndicator;

    public GeneralDataDTO() {
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