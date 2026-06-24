package com.example.app.controller;

import com.example.app.dto.UrbanPropertyDTO;
import com.example.app.service.UrbanPropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/urban-properties")
@Tag(name = "Urban Properties", description = "Urban property management APIs")
public class UrbanPropertyRestController {

    private final UrbanPropertyService urbanPropertyService;

    public UrbanPropertyRestController(UrbanPropertyService urbanPropertyService) {
        this.urbanPropertyService = urbanPropertyService;
    }

    @GetMapping
    @Operation(summary = "Get all urban properties")
    public ResponseEntity<List<UrbanPropertyDTO>> findAll() {
        return ResponseEntity.ok(urbanPropertyService.findAll());
    }

    @GetMapping("/{presentationYear}/{taxType}/{presentationCode}/{taxpayerNif}/{assetSequence}")
    @Operation(summary = "Get urban property by ID")
    public ResponseEntity<UrbanPropertyDTO> findById(
            @PathVariable Integer presentationYear,
            @PathVariable String taxType,
            @PathVariable String presentationCode,
            @PathVariable String taxpayerNif,
            @PathVariable Integer assetSequence) {
        return urbanPropertyService.findById(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create new urban property")
    public ResponseEntity<UrbanPropertyDTO> create(@Valid @RequestBody UrbanPropertyDTO dto) {
        UrbanPropertyDTO saved = urbanPropertyService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{presentationYear}/{taxType}/{presentationCode}/{taxpayerNif}/{assetSequence}")
    @Operation(summary = "Update urban property")
    public ResponseEntity<UrbanPropertyDTO> update(
            @PathVariable Integer presentationYear,
            @PathVariable String taxType,
            @PathVariable String presentationCode,
            @PathVariable String taxpayerNif,
            @PathVariable Integer assetSequence,
            @Valid @RequestBody UrbanPropertyDTO dto) {
        dto.setPresentationYear(presentationYear);
        dto.setTaxType(taxType);
        dto.setPresentationCode(presentationCode);
        dto.setTaxpayerNif(taxpayerNif);
        dto.setAssetSequence(assetSequence);
        UrbanPropertyDTO updated = urbanPropertyService.save(dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{presentationYear}/{taxType}/{presentationCode}/{taxpayerNif}/{assetSequence}")
    @Operation(summary = "Delete urban property")
    public ResponseEntity<Void> delete(
            @PathVariable Integer presentationYear,
            @PathVariable String taxType,
            @PathVariable String presentationCode,
            @PathVariable String taxpayerNif,
            @PathVariable Integer assetSequence) {
        urbanPropertyService.deleteById(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
        return ResponseEntity.noContent().build();
    }
}