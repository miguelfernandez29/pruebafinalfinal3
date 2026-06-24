package com.example.app.controller;

import com.example.app.dto.RuralPropertyDTO;
import com.example.app.service.RuralPropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/rural-properties")
@Tag(name = "Rural Properties", description = "Rural property management APIs")
public class RuralPropertyRestController {

    private final RuralPropertyService ruralPropertyService;

    public RuralPropertyRestController(RuralPropertyService ruralPropertyService) {
        this.ruralPropertyService = ruralPropertyService;
    }

    @GetMapping
    @Operation(summary = "Get all rural properties")
    public ResponseEntity<List<RuralPropertyDTO>> findAll() {
        return ResponseEntity.ok(ruralPropertyService.findAll());
    }

    @GetMapping("/{presentationYear}/{taxType}/{presentationCode}/{taxpayerNif}/{assetSequence}")
    @Operation(summary = "Get rural property by ID")
    public ResponseEntity<RuralPropertyDTO> findById(
            @PathVariable Integer presentationYear,
            @PathVariable String taxType,
            @PathVariable String presentationCode,
            @PathVariable String taxpayerNif,
            @PathVariable Integer assetSequence) {
        return ruralPropertyService.findById(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create new rural property")
    public ResponseEntity<RuralPropertyDTO> create(@Valid @RequestBody RuralPropertyDTO dto) {
        RuralPropertyDTO saved = ruralPropertyService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{presentationYear}/{taxType}/{presentationCode}/{taxpayerNif}/{assetSequence}")
    @Operation(summary = "Update rural property")
    public ResponseEntity<RuralPropertyDTO> update(
            @PathVariable Integer presentationYear,
            @PathVariable String taxType,
            @PathVariable String presentationCode,
            @PathVariable String taxpayerNif,
            @PathVariable Integer assetSequence,
            @Valid @RequestBody RuralPropertyDTO dto) {
        dto.setPresentationYear(presentationYear);
        dto.setTaxType(taxType);
        dto.setPresentationCode(presentationCode);
        dto.setTaxpayerNif(taxpayerNif);
        dto.setAssetSequence(assetSequence);
        RuralPropertyDTO updated = ruralPropertyService.save(dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{presentationYear}/{taxType}/{presentationCode}/{taxpayerNif}/{assetSequence}")
    @Operation(summary = "Delete rural property")
    public ResponseEntity<Void> delete(
            @PathVariable Integer presentationYear,
            @PathVariable String taxType,
            @PathVariable String presentationCode,
            @PathVariable String taxpayerNif,
            @PathVariable Integer assetSequence) {
        ruralPropertyService.deleteById(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
        return ResponseEntity.noContent().build();
    }
}