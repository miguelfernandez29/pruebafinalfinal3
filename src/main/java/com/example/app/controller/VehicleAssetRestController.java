package com.example.app.controller;

import com.example.app.dto.VehicleAssetDto;
import com.example.app.service.VehicleAssetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/vehicle-assets")
@Tag(name = "Vehicle Asset", description = "Vehicle Asset management APIs")
public class VehicleAssetRestController {

    private final VehicleAssetService vehicleAssetService;

    public VehicleAssetRestController(VehicleAssetService vehicleAssetService) {
        this.vehicleAssetService = vehicleAssetService;
    }

    @GetMapping
    @Operation(summary = "Get all vehicle assets")
    public ResponseEntity<List<VehicleAssetDto>> findAll() {
        return ResponseEntity.ok(vehicleAssetService.findAll());
    }

    @GetMapping("/{presentationYear}/{taxTypeCode}/{presentationCode}/{causeNif}/{subCauseCode}/{assetSequence}")
    @Operation(summary = "Get vehicle asset by ID")
    public ResponseEntity<VehicleAssetDto> findById(
            @PathVariable Integer presentationYear,
            @PathVariable String taxTypeCode,
            @PathVariable String presentationCode,
            @PathVariable String causeNif,
            @PathVariable String subCauseCode,
            @PathVariable Integer assetSequence) {
        return ResponseEntity.ok(vehicleAssetService.findById(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence));
    }

    @GetMapping("/license-plate/{licensePlate}")
    @Operation(summary = "Get vehicle asset by license plate")
    public ResponseEntity<VehicleAssetDto> findByLicensePlate(@PathVariable String licensePlate) {
        return ResponseEntity.ok(vehicleAssetService.findByLicensePlate(licensePlate));
    }

    @GetMapping("/type-brand")
    @Operation(summary = "Get vehicle assets by type and brand")
    public ResponseEntity<List<VehicleAssetDto>> findByTypeAndBrand(
            @RequestParam String vehicleTypeCode,
            @RequestParam String vehicleBrandCode) {
        return ResponseEntity.ok(vehicleAssetService.findByTypeAndBrand(vehicleTypeCode, vehicleBrandCode));
    }

    @PostMapping
    @Operation(summary = "Create a new vehicle asset")
    public ResponseEntity<VehicleAssetDto> create(@Valid @RequestBody VehicleAssetDto dto) {
        return new ResponseEntity<>(vehicleAssetService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{presentationYear}/{taxTypeCode}/{presentationCode}/{causeNif}/{subCauseCode}/{assetSequence}")
    @Operation(summary = "Update a vehicle asset")
    public ResponseEntity<VehicleAssetDto> update(
            @PathVariable Integer presentationYear,
            @PathVariable String taxTypeCode,
            @PathVariable String presentationCode,
            @PathVariable String causeNif,
            @PathVariable String subCauseCode,
            @PathVariable Integer assetSequence,
            @Valid @RequestBody VehicleAssetDto dto) {
        return ResponseEntity.ok(vehicleAssetService.update(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence, dto));
    }

    @DeleteMapping("/{presentationYear}/{taxTypeCode}/{presentationCode}/{causeNif}/{subCauseCode}/{assetSequence}")
    @Operation(summary = "Delete a vehicle asset")
    public ResponseEntity<Void> delete(
            @PathVariable Integer presentationYear,
            @PathVariable String taxTypeCode,
            @PathVariable String presentationCode,
            @PathVariable String causeNif,
            @PathVariable String subCauseCode,
            @PathVariable Integer assetSequence) {
        vehicleAssetService.delete(presentationYear, taxTypeCode, presentationCode, causeNif, subCauseCode, assetSequence);
        return ResponseEntity.noContent().build();
    }
}