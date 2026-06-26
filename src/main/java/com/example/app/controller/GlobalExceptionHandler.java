package com.example.app.controller;

import com.example.app.exception.AssetDeletionException;
import com.example.app.exception.BusinessValidationException;
import com.example.app.exception.DuplicateAssetException;
import com.example.app.exception.InvalidCadastralReferenceException;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.exception.ValueConformityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleResourceNotFound(ResourceNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Resource Not Found");
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessValidationException.class)
    public ResponseEntity<Map<String, String>> handleBusinessValidation(BusinessValidationException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Business Validation Error");
        error.put("code", ex.getErrorCode());
        error.put("field", ex.getFieldName());
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCadastralReferenceException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCadastralReference(InvalidCadastralReferenceException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Invalid Cadastral Reference");
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateAssetException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateAsset(DuplicateAssetException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Duplicate Asset");
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AssetDeletionException.class)
    public ResponseEntity<Map<String, String>> handleAssetDeletion(AssetDeletionException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Asset Deletion Error");
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ValueConformityException.class)
    public ResponseEntity<Map<String, String>> handleValueConformity(ValueConformityException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Value Conformity Error");
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "Validation Error");
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Internal Server Error");
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}