package com.example.app.exception;

public class InvalidCadastralReferenceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String cadastralReference;
    private String expectedFormat;

    public InvalidCadastralReferenceException(String message) {
        super(message);
    }

    public InvalidCadastralReferenceException(String cadastralReference, String expectedFormat) {
        super(String.format("Invalid cadastral reference '%s'. Expected format: %s", cadastralReference, expectedFormat));
        this.cadastralReference = cadastralReference;
        this.expectedFormat = expectedFormat;
    }

    public String getCadastralReference() {
        return cadastralReference;
    }

    public String getExpectedFormat() {
        return expectedFormat;
    }
}