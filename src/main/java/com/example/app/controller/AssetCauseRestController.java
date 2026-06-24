package com.example.app.controller;

import com.example.app.dto.AssetCauseDTO;
import com.example.app.service.AssetCauseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/asset-causes")
@Tag(name = "Asset Causes", description = "Asset cause management APIs")
public class AssetCauseRestController {

    private final AssetCauseService assetCauseService;

    public AssetCauseRestController(AssetCauseService assetCauseService) {
        this.assetCauseService = assetCauseService;
    }

    @GetMapping
    @Operation(summary = "Get all asset causes")
    public ResponseEntity<List<AssetCauseDTO>> findAll() {
        return ResponseEntity.ok(assetCauseService.findAll());
    }

    @GetMapping("/{presentationYear}/{taxType}/{presentationCode}/{taxpayerNif}/{assetSequence}")
    @Operation(summary = "Get asset cause by ID")
    public ResponseEntity<AssetCauseDTO> findById(
            @PathVariable Integer presentationYear,
            @PathVariable String taxType,
            @PathVariable String presentationCode,
            @PathVariable String taxpayerNif,
            @PathVariable Integer assetSequence) {
        return assetCauseService.findById(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/declaration/{presentationYear}/{taxType}/{presentationCode}/{taxpayerNif}")
    @Operation(summary = "Get asset causes by declaration")
    public ResponseEntity<List<AssetCauseDTO>> findByDeclaration(
            @PathVariable Integer presentationYear,
            @PathVariable String taxType,
            @PathVariable String presentationCode,
            @PathVariable String taxpayerNif) {
        return ResponseEntity.ok(assetCauseService.findByDeclaration(presentationYear, taxType, presentationCode, taxpayerNif));
    }

    @GetMapping("/next-sequence/{presentationYear}/{taxType}/{presentationCode}/{taxpayerNif}")
    @Operation(summary = "Get next asset sequence number")
    public ResponseEntity<Integer> getNextSequence(
            @PathVariable Integer presentationYear,
            @PathVariable String taxType,
            @PathVariable String presentationCode,
            @PathVariable String taxpayerNif) {
        return ResponseEntity.ok(assetCauseService.getNextAssetSequence(presentationYear, taxType, presentationCode, taxpayerNif));
    }

    @PostMapping
    @Operation(summary = "Create new asset cause")
    public ResponseEntity<AssetCauseDTO> create(@Valid @RequestBody AssetCauseDTO dto) {
        AssetCauseDTO saved = assetCauseService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{presentationYear}/{taxType}/{presentationCode}/{taxpayerNif}/{assetSequence}")
    @Operation(summary = "Update asset cause")
    public ResponseEntity<AssetCauseDTO> update(
            @PathVariable Integer presentationYear,
            @PathVariable String taxType,
            @PathVariable String presentationCode,
            @PathVariable String taxpayerNif,
            @PathVariable Integer assetSequence,
            @Valid @RequestBody AssetCauseDTO dto) {
        dto.setPresentationYear(presentationYear);
        dto.setTaxType(taxType);
        dto.setPresentationCode(presentationCode);
        dto.setTaxpayerNif(taxpayerNif);
        dto.setAssetSequence(assetSequence);
        AssetCauseDTO updated = assetCauseService.save(dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{presentationYear}/{taxType}/{presentationCode}/{taxpayerNif}/{assetSequence}")
    @Operation(summary = "Delete asset cause")
    public ResponseEntity<Void> delete(
            @PathVariable Integer presentationYear,
            @PathVariable String taxType,
            @PathVariable String presentationCode,
            @PathVariable String taxpayerNif,
            @PathVariable Integer assetSequence) {
        assetCauseService.deleteById(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
        return ResponseEntity.noContent().build();
    }
}