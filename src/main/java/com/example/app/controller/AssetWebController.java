package com.example.app.controller;

import com.example.app.dto.AssetCauseDTO;
import com.example.app.dto.UrbanPropertyDTO;
import com.example.app.dto.RuralPropertyDTO;
import com.example.app.dto.VehicleDTO;
import com.example.app.service.AssetCauseService;
import com.example.app.service.UrbanPropertyService;
import com.example.app.service.RuralPropertyService;
import com.example.app.service.VehicleService;
import com.example.app.service.ReferenceDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/assets")
public class AssetWebController {

    private final AssetCauseService assetCauseService;
    private final UrbanPropertyService urbanPropertyService;
    private final RuralPropertyService ruralPropertyService;
    private final VehicleService vehicleService;
    private final ReferenceDataService referenceDataService;

    public AssetWebController(AssetCauseService assetCauseService,
                              UrbanPropertyService urbanPropertyService,
                              RuralPropertyService ruralPropertyService,
                              VehicleService vehicleService,
                              ReferenceDataService referenceDataService) {
        this.assetCauseService = assetCauseService;
        this.urbanPropertyService = urbanPropertyService;
        this.ruralPropertyService = ruralPropertyService;
        this.vehicleService = vehicleService;
        this.referenceDataService = referenceDataService;
    }

    @GetMapping
    public String listAssets(Model model) {
        List<AssetCauseDTO> assets = assetCauseService.findAll();
        model.addAttribute("assets", assets);
        return "assets/list";
    }

    @GetMapping("/declaration/{presentationYear}/{taxType}/{presentationCode}/{taxpayerNif}")
    public String listAssetsByDeclaration(
            @PathVariable Integer presentationYear,
            @PathVariable String taxType,
            @PathVariable String presentationCode,
            @PathVariable String taxpayerNif,
            Model model) {
        List<AssetCauseDTO> assets = assetCauseService.findByDeclaration(presentationYear, taxType, presentationCode, taxpayerNif);
        model.addAttribute("assets", assets);
        model.addAttribute("presentationYear", presentationYear);
        model.addAttribute("taxType", taxType);
        model.addAttribute("presentationCode", presentationCode);
        model.addAttribute("taxpayerNif", taxpayerNif);
        model.addAttribute("assetNatures", referenceDataService.getActiveGeneralData("110"));
        model.addAttribute("assetPositions", referenceDataService.getActiveGeneralData("104"));
        return "assets/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("assetCause", new AssetCauseDTO());
        model.addAttribute("assetNatures", referenceDataService.getActiveGeneralData("110"));
        model.addAttribute("assetPositions", referenceDataService.getActiveGeneralData("104"));
        return "assets/form";
    }

    @PostMapping("/save")
    public String saveAsset(@Valid @ModelAttribute("assetCause") AssetCauseDTO dto,
                            BindingResult result,
                            RedirectAttributes redirectAttributes,
                            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("assetNatures", referenceDataService.getActiveGeneralData("110"));
            model.addAttribute("assetPositions", referenceDataService.getActiveGeneralData("104"));
            return "assets/form";
        }
        try {
            assetCauseService.save(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Bien guardado correctamente");
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("assetNatures", referenceDataService.getActiveGeneralData("110"));
            model.addAttribute("assetPositions", referenceDataService.getActiveGeneralData("104"));
            return "assets/form";
        }
        return "redirect:/assets";
    }

    @GetMapping("/urban/{presentationYear}/{taxType}/{presentationCode}/{taxpayerNif}/{assetSequence}")
    public String showUrbanPropertyDetail(
            @PathVariable Integer presentationYear,
            @PathVariable String taxType,
            @PathVariable String presentationCode,
            @PathVariable String taxpayerNif,
            @PathVariable Integer assetSequence,
            Model model) {
        UrbanPropertyDTO urbanProperty = urbanPropertyService.findById(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence)
                .orElse(new UrbanPropertyDTO());
        model.addAttribute("urbanProperty", urbanProperty);
        model.addAttribute("provinces", referenceDataService.getActiveProvinces());
        model.addAttribute("propertyTypes", referenceDataService.getActivePropertyTypes("U"));
        return "assets/urban-property";
    }

    @PostMapping("/urban/save")
    public String saveUrbanProperty(@Valid @ModelAttribute("urbanProperty") UrbanPropertyDTO dto,
                                    BindingResult result,
                                    RedirectAttributes redirectAttributes,
                                    Model model) {
        if (result.hasErrors()) {
            model.addAttribute("provinces", referenceDataService.getActiveProvinces());
            model.addAttribute("propertyTypes", referenceDataService.getActivePropertyTypes("U"));
            return "assets/urban-property";
        }
        try {
            urbanPropertyService.save(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Bien urbano guardado correctamente");
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("provinces", referenceDataService.getActiveProvinces());
            model.addAttribute("propertyTypes", referenceDataService.getActivePropertyTypes("U"));
            return "assets/urban-property";
        }
        return "redirect:/assets";
    }

    @GetMapping("/rural/{presentationYear}/{taxType}/{presentationCode}/{taxpayerNif}/{assetSequence}")
    public String showRuralPropertyDetail(
            @PathVariable Integer presentationYear,
            @PathVariable String taxType,
            @PathVariable String presentationCode,
            @PathVariable String taxpayerNif,
            @PathVariable Integer assetSequence,
            Model model) {
        RuralPropertyDTO ruralProperty = ruralPropertyService.findById(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence)
                .orElse(new RuralPropertyDTO());
        model.addAttribute("ruralProperty", ruralProperty);
        model.addAttribute("provinces", referenceDataService.getActiveProvinces());
        model.addAttribute("propertyTypes", referenceDataService.getActivePropertyTypes("R"));
        return "assets/rural-property";
    }

    @PostMapping("/rural/save")
    public String saveRuralProperty(@Valid @ModelAttribute("ruralProperty") RuralPropertyDTO dto,
                                    BindingResult result,
                                    RedirectAttributes redirectAttributes,
                                    Model model) {
        if (result.hasErrors()) {
            model.addAttribute("provinces", referenceDataService.getActiveProvinces());
            model.addAttribute("propertyTypes", referenceDataService.getActivePropertyTypes("R"));
            return "assets/rural-property";
        }
        try {
            ruralPropertyService.save(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Bien rustico guardado correctamente");
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("provinces", referenceDataService.getActiveProvinces());
            model.addAttribute("propertyTypes", referenceDataService.getActivePropertyTypes("R"));
            return "assets/rural-property";
        }
        return "redirect:/assets";
    }

    @GetMapping("/vehicle/{presentationYear}/{taxType}/{presentationCode}/{taxpayerNif}/{assetSequence}")
    public String showVehicleDetail(
            @PathVariable Integer presentationYear,
            @PathVariable String taxType,
            @PathVariable String presentationCode,
            @PathVariable String taxpayerNif,
            @PathVariable Integer assetSequence,
            Model model) {
        VehicleDTO vehicle = vehicleService.findById(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence)
                .orElse(new VehicleDTO());
        model.addAttribute("vehicle", vehicle);
        return "assets/vehicle";
    }

    @PostMapping("/vehicle/save")
    public String saveVehicle(@Valid @ModelAttribute("vehicle") VehicleDTO dto,
                              BindingResult result,
                              RedirectAttributes redirectAttributes,
                              Model model) {
        if (result.hasErrors()) {
            return "assets/vehicle";
        }
        try {
            vehicleService.save(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Vehiculo guardado correctamente");
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "assets/vehicle";
        }
        return "redirect:/assets";
    }

    @GetMapping("/delete/{presentationYear}/{taxType}/{presentationCode}/{taxpayerNif}/{assetSequence}")
    public String deleteAsset(
            @PathVariable Integer presentationYear,
            @PathVariable String taxType,
            @PathVariable String presentationCode,
            @PathVariable String taxpayerNif,
            @PathVariable Integer assetSequence,
            RedirectAttributes redirectAttributes) {
        assetCauseService.deleteById(presentationYear, taxType, presentationCode, taxpayerNif, assetSequence);
        redirectAttributes.addFlashAttribute("successMessage", "Bien eliminado correctamente");
        return "redirect:/assets";
    }
}