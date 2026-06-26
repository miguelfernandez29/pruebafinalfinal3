package com.example.app.controller;

import com.example.app.dto.RusticPropertyDto;
import com.example.app.service.RusticPropertyService;
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
@RequestMapping("/api/rustic-properties")
@Tag(name = "Rustic Property", description = "Rustic Property management APIs")
public class RusticPropertyRestController {

    private final RusticPropertyService rusticPropertyService;

    public RusticPropertyRestController(RusticPropertyService rusticPropertyService) {
        this.rusticPropertyService = rusticPropertyService;
    }

    @GetMapping
    @Operation(summary = "Get all rustic properties")
    public ResponseEntity<List<RusticPropertyDto>> findAll() {
        return ResponseEntity.ok(rusticPropertyService.findAll());
    }

    @GetMapping("/{presentationYear}/{taxTypeCode}/{presentationCode}/{causeNif}/{subCauseCode}/{assetSequence}")
    @Operation(summary = "Get rustic property by ID")
    public ResponseEntity<RusticPropertyDto> findById(
            @PathVariable Integer presentationYear,
            @PathVariable String taxTypeCode,
            @PathVariable String presentationCode,
            @PathVariable String causeNif,
            @PathVariable String subCauseCode,
            @PathVariable Integer assetSequence) {
        return ResponseEntity.ok(rusticPropertyService.findById(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence));
    }

    @GetMapping("/cadastral/{cadastralReference}")
    @Operation(summary = "Get rustic properties by cadastral reference")
    public ResponseEntity<List<RusticPropertyDto>> findByCadastralReference(
            @PathVariable String cadastralReference) {
        return ResponseEntity.ok(rusticPropertyService.findByCadastralReference(cadastralReference));
    }

    @GetMapping("/location")
    @Operation(summary = "Get rustic properties by location")
    public ResponseEntity<List<RusticPropertyDto>> findByLocation(
            @RequestParam String provinceCode,
            @RequestParam String municipalityCode,
            @RequestParam String polygon,
            @RequestParam String parcel) {
        return ResponseEntity.ok(rusticPropertyService.findByLocation(provinceCode, municipalityCode, polygon, parcel));
    }

    @PostMapping
    @Operation(summary = "Create a new rustic property")
    public ResponseEntity<RusticPropertyDto> create(@Valid @RequestBody RusticPropertyDto dto) {
        return new ResponseEntity<>(rusticPropertyService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{presentationYear}/{taxTypeCode}/{presentationCode}/{causeNif}/{subCauseCode}/{assetSequence}")
    @Operation(summary = "Update a rustic property")
    public ResponseEntity<RusticPropertyDto> update(
            @PathVariable Integer presentationYear,
            @PathVariable String taxTypeCode,
            @PathVariable String presentationCode,
            @PathVariable String causeNif,
            @PathVariable String subCauseCode,
            @PathVariable Integer assetSequence,
            @Valid @RequestBody RusticPropertyDto dto) {
        return ResponseEntity.ok(rusticPropertyService.update(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence, dto));
    }

    @DeleteMapping("/{presentationYear}/{taxTypeCode}/{presentationCode}/{causeNif}/{subCauseCode}/{assetSequence}")
    @Operation(summary = "Delete a rustic property")
    public ResponseEntity<Void> delete(
            @PathVariable Integer presentationYear,
            @PathVariable String taxTypeCode,
            @PathVariable String presentationCode,
            @PathVariable String causeNif,
            @PathVariable String subCauseCode,
            @PathVariable Integer assetSequence) {
        rusticPropertyService.delete(presentationYear, taxTypeCode, presentationCode, causeNif, subCauseCode, assetSequence);
        return ResponseEntity.noContent().build();
    }
}