package com.example.app.exception;

import java.math.BigDecimal;

public class ValueConformityException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private BigDecimal declaredValue;
    private BigDecimal referenceValue;
    private String conformityStatus;

    public ValueConformityException(String message) {
        super(message);
    }

    public ValueConformityException(BigDecimal declaredValue, BigDecimal referenceValue, String conformityStatus) {
        super(String.format("Value conformity issue: declared=%s, reference=%s, status=%s",
                declaredValue, referenceValue, conformityStatus));
        this.declaredValue = declaredValue;
        this.referenceValue = referenceValue;
        this.conformityStatus = conformityStatus;
    }

    public BigDecimal getDeclaredValue() {
        return declaredValue;
    }

    public BigDecimal getReferenceValue() {
        return referenceValue;
    }

    public String getConformityStatus() {
        return conformityStatus;
    }
}