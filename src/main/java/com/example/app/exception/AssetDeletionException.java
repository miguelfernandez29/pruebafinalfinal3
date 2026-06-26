package com.example.app.exception;

public class AssetDeletionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String assetType;
    private Integer assetSequence;
    private String reason;

    public AssetDeletionException(String message) {
        super(message);
    }

    public AssetDeletionException(String assetType, Integer assetSequence, String reason) {
        super(String.format("Cannot delete %s with sequence %d: %s", assetType, assetSequence, reason));
        this.assetType = assetType;
        this.assetSequence = assetSequence;
        this.reason = reason;
    }

    public String getAssetType() {
        return assetType;
    }

    public Integer getAssetSequence() {
        return assetSequence;
    }

    public String getReason() {
        return reason;
    }
}