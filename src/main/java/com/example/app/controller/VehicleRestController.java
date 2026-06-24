package com.example.app.controller;

import com.example.app.dto.VehicleDTO;
import com.example.app.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@Tag(name = "Vehicles", description = "Vehicle management APIs")
public class VehicleRestController {

    private final VehicleService vehicleService;

    public VehicleRestController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    @Operation(summary = "Get all vehicles")
    public ResponseEntity<List<VehicleDTO>> findAll() {
        return ResponseEntity.ok(vehicleService.findAll());
    }

    @GetMapping("/{presentationYear}/{taxType}/{presentationCode}/{taxpayerNif}/{assetSequence}")
    @Operation(summary = "Get vehicle by ID")
    public ResponseEntity<VehicleDTO> findById(
            @PathVariable Integer presentationYear,
            @PathVariable String taxType,
            @PathVariable String presentationCode,
            @PathVariable String taxpayerNif,
            @PathVariable Integer assetSequence) {
        return vehicleService.findById(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create new vehicle")
    public ResponseEntity<VehicleDTO> create(@Valid @RequestBody VehicleDTO dto) {
        VehicleDTO saved = vehicleService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{presentationYear}/{taxType}/{presentationCode}/{taxpayerNif}/{assetSequence}")
    @Operation(summary = "Update vehicle")
    public ResponseEntity<VehicleDTO> update(
            @PathVariable Integer presentationYear,
            @PathVariable String taxType,
            @PathVariable String presentationCode,
            @PathVariable String taxpayerNif,
            @PathVariable Integer assetSequence,
            @Valid @RequestBody VehicleDTO dto) {
        dto.setPresentationYear(presentationYear);
        dto.setTaxType(taxType);
        dto.setPresentationCode(presentationCode);
        dto.setTaxpayerNif(taxpayerNif);
        dto.setAssetSequence(assetSequence);
        VehicleDTO updated = vehicleService.save(dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{presentationYear}/{taxType}/{presentationCode}/{taxpayerNif}/{assetSequence}")
    @Operation(summary = "Delete vehicle")
    public ResponseEntity<Void> delete(
            @PathVariable Integer presentationYear,
            @PathVariable String taxType,
            @PathVariable String presentationCode,
            @PathVariable String taxpayerNif,
            @PathVariable Integer assetSequence) {
        vehicleService.deleteById(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
        return ResponseEntity.noContent().build();
    }
}