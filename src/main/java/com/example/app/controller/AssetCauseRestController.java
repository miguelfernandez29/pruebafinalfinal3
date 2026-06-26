package com.example.app.controller;

import com.example.app.dto.AssetCauseDto;
import com.example.app.service.AssetCauseService;
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
@RequestMapping("/api/asset-causes")
@Tag(name = "Asset Cause", description = "Asset Cause management APIs")
public class AssetCauseRestController {

    private final AssetCauseService assetCauseService;

    public AssetCauseRestController(AssetCauseService assetCauseService) {
        this.assetCauseService = assetCauseService;
    }

    @GetMapping
    @Operation(summary = "Get all asset causes")
    public ResponseEntity<List<AssetCauseDto>> findAll() {
        return ResponseEntity.ok(assetCauseService.findAll());
    }

    @GetMapping("/{presentationYear}/{taxTypeCode}/{presentationCode}/{causeNif}/{subCauseCode}/{assetSequence}")
    @Operation(summary = "Get asset cause by ID")
    public ResponseEntity<AssetCauseDto> findById(
            @PathVariable Integer presentationYear,
            @PathVariable String taxTypeCode,
            @PathVariable String presentationCode,
            @PathVariable String causeNif,
            @PathVariable String subCauseCode,
            @PathVariable Integer assetSequence) {
        return ResponseEntity.ok(assetCauseService.findById(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence));
    }

    @GetMapping("/declaration")
    @Operation(summary = "Get asset causes by declaration")
    public ResponseEntity<List<AssetCauseDto>> findByDeclaration(
            @RequestParam Integer presentationYear,
            @RequestParam String taxTypeCode,
            @RequestParam String presentationCode,
            @RequestParam String causeNif,
            @RequestParam String subCauseCode) {
        return ResponseEntity.ok(assetCauseService.findByDeclaration(presentationYear, taxTypeCode,
                presentationCode, causeNif, subCauseCode));
    }

    @PostMapping
    @Operation(summary = "Create a new asset cause")
    public ResponseEntity<AssetCauseDto> create(@Valid @RequestBody AssetCauseDto dto) {
        return new ResponseEntity<>(assetCauseService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{presentationYear}/{taxTypeCode}/{presentationCode}/{causeNif}/{subCauseCode}/{assetSequence}")
    @Operation(summary = "Update an asset cause")
    public ResponseEntity<AssetCauseDto> update(
            @PathVariable Integer presentationYear,
            @PathVariable String taxTypeCode,
            @PathVariable String presentationCode,
            @PathVariable String causeNif,
            @PathVariable String subCauseCode,
            @PathVariable Integer assetSequence,
            @Valid @RequestBody AssetCauseDto dto) {
        return ResponseEntity.ok(assetCauseService.update(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence, dto));
    }

    @DeleteMapping("/{presentationYear}/{taxTypeCode}/{presentationCode}/{causeNif}/{subCauseCode}/{assetSequence}")
    @Operation(summary = "Delete an asset cause")
    public ResponseEntity<Void> delete(
            @PathVariable Integer presentationYear,
            @PathVariable String taxTypeCode,
            @PathVariable String presentationCode,
            @PathVariable String causeNif,
            @PathVariable String subCauseCode,
            @PathVariable Integer assetSequence) {
        assetCauseService.delete(presentationYear, taxTypeCode, presentationCode, causeNif, subCauseCode, assetSequence);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{presentationYear}/{taxTypeCode}/{presentationCode}/{causeNif}/{subCauseCode}/{assetSequence}/conformity")
    @Operation(summary = "Update conformity status")
    public ResponseEntity<Void> updateConformity(
            @PathVariable Integer presentationYear,
            @PathVariable String taxTypeCode,
            @PathVariable String presentationCode,
            @PathVariable String causeNif,
            @PathVariable String subCauseCode,
            @PathVariable Integer assetSequence,
            @RequestParam String conformityIndicator) {
        assetCauseService.updateConformityStatus(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence, conformityIndicator);
        return ResponseEntity.ok().build();
    }
}