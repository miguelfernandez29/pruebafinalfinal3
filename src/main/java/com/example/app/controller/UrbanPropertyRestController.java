package com.example.app.controller;

import com.example.app.dto.UrbanPropertyDto;
import com.example.app.service.UrbanPropertyService;
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
@RequestMapping("/api/urban-properties")
@Tag(name = "Urban Property", description = "Urban Property management APIs")
public class UrbanPropertyRestController {

    private final UrbanPropertyService urbanPropertyService;

    public UrbanPropertyRestController(UrbanPropertyService urbanPropertyService) {
        this.urbanPropertyService = urbanPropertyService;
    }

    @GetMapping
    @Operation(summary = "Get all urban properties")
    public ResponseEntity<List<UrbanPropertyDto>> findAll() {
        return ResponseEntity.ok(urbanPropertyService.findAll());
    }

    @GetMapping("/{presentationYear}/{taxTypeCode}/{presentationCode}/{causeNif}/{subCauseCode}/{assetSequence}")
    @Operation(summary = "Get urban property by ID")
    public ResponseEntity<UrbanPropertyDto> findById(
            @PathVariable Integer presentationYear,
            @PathVariable String taxTypeCode,
            @PathVariable String presentationCode,
            @PathVariable String causeNif,
            @PathVariable String subCauseCode,
            @PathVariable Integer assetSequence) {
        return ResponseEntity.ok(urbanPropertyService.findById(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence));
    }

    @GetMapping("/cadastral/{cadastralReference}")
    @Operation(summary = "Get urban properties by cadastral reference")
    public ResponseEntity<List<UrbanPropertyDto>> findByCadastralReference(
            @PathVariable String cadastralReference) {
        return ResponseEntity.ok(urbanPropertyService.findByCadastralReference(cadastralReference));
    }

    @GetMapping("/location")
    @Operation(summary = "Get urban properties by province and municipality")
    public ResponseEntity<List<UrbanPropertyDto>> findByProvinceAndMunicipality(
            @RequestParam String provinceCode,
            @RequestParam String municipalityCode) {
        return ResponseEntity.ok(urbanPropertyService.findByProvinceAndMunicipality(provinceCode, municipalityCode));
    }

    @PostMapping
    @Operation(summary = "Create a new urban property")
    public ResponseEntity<UrbanPropertyDto> create(@Valid @RequestBody UrbanPropertyDto dto) {
        return new ResponseEntity<>(urbanPropertyService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{presentationYear}/{taxTypeCode}/{presentationCode}/{causeNif}/{subCauseCode}/{assetSequence}")
    @Operation(summary = "Update an urban property")
    public ResponseEntity<UrbanPropertyDto> update(
            @PathVariable Integer presentationYear,
            @PathVariable String taxTypeCode,
            @PathVariable String presentationCode,
            @PathVariable String causeNif,
            @PathVariable String subCauseCode,
            @PathVariable Integer assetSequence,
            @Valid @RequestBody UrbanPropertyDto dto) {
        return ResponseEntity.ok(urbanPropertyService.update(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence, dto));
    }

    @DeleteMapping("/{presentationYear}/{taxTypeCode}/{presentationCode}/{causeNif}/{subCauseCode}/{assetSequence}")
    @Operation(summary = "Delete an urban property")
    public ResponseEntity<Void> delete(
            @PathVariable Integer presentationYear,
            @PathVariable String taxTypeCode,
            @PathVariable String presentationCode,
            @PathVariable String causeNif,
            @PathVariable String subCauseCode,
            @PathVariable Integer assetSequence) {
        urbanPropertyService.delete(presentationYear, taxTypeCode, presentationCode, causeNif, subCauseCode, assetSequence);
        return ResponseEntity.noContent().build();
    }
}