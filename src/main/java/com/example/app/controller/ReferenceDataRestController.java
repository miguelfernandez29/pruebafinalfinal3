package com.example.app.controller;

import com.example.app.dto.GeneralDataDTO;
import com.example.app.dto.PropertyTypeDTO;
import com.example.app.dto.ProvinceDTO;
import com.example.app.dto.MunicipalityDTO;
import com.example.app.service.ReferenceDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reference-data")
@Tag(name = "Reference Data", description = "Reference data APIs")
public class ReferenceDataRestController {

    private final ReferenceDataService referenceDataService;

    public ReferenceDataRestController(ReferenceDataService referenceDataService) {
        this.referenceDataService = referenceDataService;
    }

    @GetMapping("/general-data/{dataType}")
    @Operation(summary = "Get active general data by type")
    public ResponseEntity<List<GeneralDataDTO>> getGeneralData(@PathVariable String dataType) {
        return ResponseEntity.ok(referenceDataService.getActiveGeneralData(dataType));
    }

    @GetMapping("/property-types/{assetNatureCode}")
    @Operation(summary = "Get active property types by asset nature")
    public ResponseEntity<List<PropertyTypeDTO>> getPropertyTypes(@PathVariable String assetNatureCode) {
        return ResponseEntity.ok(referenceDataService.getActivePropertyTypes(assetNatureCode));
    }

    @GetMapping("/provinces")
    @Operation(summary = "Get active provinces")
    public ResponseEntity<List<ProvinceDTO>> getProvinces() {
        return ResponseEntity.ok(referenceDataService.getActiveProvinces());
    }

    @GetMapping("/municipalities/{provinceCode}")
    @Operation(summary = "Get active municipalities by province")
    public ResponseEntity<List<MunicipalityDTO>> getMunicipalities(@PathVariable String provinceCode) {
        return ResponseEntity.ok(referenceDataService.getActiveMunicipalities(provinceCode));
    }
}