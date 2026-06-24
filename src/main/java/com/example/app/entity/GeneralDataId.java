package com.example.app.entity;

import java.io.Serializable;
import java.util.Objects;

public class GeneralDataId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dataType;
    private String dataCode;

    public GeneralDataId() {
    }

    public GeneralDataId(String dataType, String dataCode) {
        this.dataType = dataType;
        this.dataCode = dataCode;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralDataId that = (GeneralDataId) o;
        return Objects.equals(dataType, that.dataType) &&
                Objects.equals(dataCode, that.dataCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataType, dataCode);
    }
}