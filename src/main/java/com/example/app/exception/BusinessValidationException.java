package com.example.app.exception;

public class BusinessValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errorCode;
    private String fieldName;

    public BusinessValidationException(String message) {
        super(message);
    }

    public BusinessValidationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessValidationException(String errorCode, String fieldName, String message) {
        super(message);
        this.errorCode = errorCode;
        this.fieldName = fieldName;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getFieldName() {
        return fieldName;
    }
}