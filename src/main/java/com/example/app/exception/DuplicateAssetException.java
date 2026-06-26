package com.example.app.exception;

public class DuplicateAssetException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String assetType;
    private String identifier;

    public DuplicateAssetException(String message) {
        super(message);
    }

    public DuplicateAssetException(String assetType, String identifier) {
        super(String.format("Duplicate %s found with identifier: %s", assetType, identifier));
        this.assetType = assetType;
        this.identifier = identifier;
    }

    public String getAssetType() {
        return assetType;
    }

    public String getIdentifier() {
        return identifier;
    }
}