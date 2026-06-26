package com.example.app.controller;

import com.example.app.dto.AssetCauseDto;
import com.example.app.dto.UrbanPropertyDto;
import com.example.app.dto.RusticPropertyDto;
import com.example.app.dto.VehicleAssetDto;
import com.example.app.service.AssetCauseService;
import com.example.app.service.UrbanPropertyService;
import com.example.app.service.RusticPropertyService;
import com.example.app.service.VehicleAssetService;
import com.example.app.service.BusinessAssetService;
import com.example.app.service.OtherAssetService;
import com.example.app.service.BankAccountAssetService;
import com.example.app.service.ListedSecuritiesService;
import com.example.app.service.UnlistedSecuritiesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/assets")
public class AssetWebController {

    private final AssetCauseService assetCauseService;
    private final UrbanPropertyService urbanPropertyService;
    private final RusticPropertyService rusticPropertyService;
    private final VehicleAssetService vehicleAssetService;
    private final BusinessAssetService businessAssetService;
    private final OtherAssetService otherAssetService;
    private final BankAccountAssetService bankAccountAssetService;
    private final ListedSecuritiesService listedSecuritiesService;
    private final UnlistedSecuritiesService unlistedSecuritiesService;

    public AssetWebController(AssetCauseService assetCauseService,
                              UrbanPropertyService urbanPropertyService,
                              RusticPropertyService rusticPropertyService,
                              VehicleAssetService vehicleAssetService,
                              BusinessAssetService businessAssetService,
                              OtherAssetService otherAssetService,
                              BankAccountAssetService bankAccountAssetService,
                              ListedSecuritiesService listedSecuritiesService,
                              UnlistedSecuritiesService unlistedSecuritiesService) {
        this.assetCauseService = assetCauseService;
        this.urbanPropertyService = urbanPropertyService;
        this.rusticPropertyService = rusticPropertyService;
        this.vehicleAssetService = vehicleAssetService;
        this.businessAssetService = businessAssetService;
        this.otherAssetService = otherAssetService;
        this.bankAccountAssetService = bankAccountAssetService;
        this.listedSecuritiesService = listedSecuritiesService;
        this.unlistedSecuritiesService = unlistedSecuritiesService;
    }

    @GetMapping
    public String listAssets(Model model) {
        model.addAttribute("assetCauses", assetCauseService.findAll());
        return "assets/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("assetCause", new AssetCauseDto());
        return "assets/form";
    }

    @PostMapping("/save")
    public String saveAsset(@Valid @ModelAttribute("assetCause") AssetCauseDto dto,
                            BindingResult result,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "assets/form";
        }
        try {
            assetCauseService.create(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Asset created successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/assets";
    }

    @GetMapping("/edit/{presentationYear}/{taxTypeCode}/{presentationCode}/{causeNif}/{subCauseCode}/{assetSequence}")
    public String showEditForm(@PathVariable Integer presentationYear,
                               @PathVariable String taxTypeCode,
                               @PathVariable String presentationCode,
                               @PathVariable String causeNif,
                               @PathVariable String subCauseCode,
                               @PathVariable Integer assetSequence,
                               Model model) {
        AssetCauseDto dto = assetCauseService.findById(presentationYear, taxTypeCode, presentationCode,
                causeNif, subCauseCode, assetSequence);
        model.addAttribute("assetCause", dto);
        model.addAttribute("isEdit", true);
        return "assets/form";
    }

    @PostMapping("/update/{presentationYear}/{taxTypeCode}/{presentationCode}/{causeNif}/{subCauseCode}/{assetSequence}")
    public String updateAsset(@PathVariable Integer presentationYear,
                              @PathVariable String taxTypeCode,
                              @PathVariable String presentationCode,
                              @PathVariable String causeNif,
                              @PathVariable String subCauseCode,
                              @PathVariable Integer assetSequence,
                              @Valid @ModelAttribute("assetCause") AssetCauseDto dto,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "assets/form";
        }
        try {
            assetCauseService.update(presentationYear, taxTypeCode, presentationCode,
                    causeNif, subCauseCode, assetSequence, dto);
            redirectAttributes.addFlashAttribute("successMessage", "Asset updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/assets";
    }

    @GetMapping("/delete/{presentationYear}/{taxTypeCode}/{presentationCode}/{causeNif}/{subCauseCode}/{assetSequence}")
    public String deleteAsset(@PathVariable Integer presentationYear,
                              @PathVariable String taxTypeCode,
                              @PathVariable String presentationCode,
                              @PathVariable String causeNif,
                              @PathVariable String subCauseCode,
                              @PathVariable Integer assetSequence,
                              RedirectAttributes redirectAttributes) {
        try {
            assetCauseService.delete(presentationYear, taxTypeCode, presentationCode,
                    causeNif, subCauseCode, assetSequence);
            redirectAttributes.addFlashAttribute("successMessage", "Asset deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/assets";
    }

    @GetMapping("/urban")
    public String listUrbanProperties(Model model) {
        model.addAttribute("urbanProperties", urbanPropertyService.findAll());
        return "assets/urban-list";
    }

    @GetMapping("/urban/new")
    public String showUrbanCreateForm(Model model) {
        model.addAttribute("urbanProperty", new UrbanPropertyDto());
        return "assets/urban-form";
    }

    @PostMapping("/urban/save")
    public String saveUrbanProperty(@Valid @ModelAttribute("urbanProperty") UrbanPropertyDto dto,
                                    BindingResult result,
                                    RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "assets/urban-form";
        }
        try {
            urbanPropertyService.create(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Urban property created successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/assets/urban";
    }

    @GetMapping("/rustic")
    public String listRusticProperties(Model model) {
        model.addAttribute("rusticProperties", rusticPropertyService.findAll());
        return "assets/rustic-list";
    }

    @GetMapping("/rustic/new")
    public String showRusticCreateForm(Model model) {
        model.addAttribute("rusticProperty", new RusticPropertyDto());
        return "assets/rustic-form";
    }

    @PostMapping("/rustic/save")
    public String saveRusticProperty(@Valid @ModelAttribute("rusticProperty") RusticPropertyDto dto,
                                     BindingResult result,
                                     RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "assets/rustic-form";
        }
        try {
            rusticPropertyService.create(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Rustic property created successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/assets/rustic";
    }

    @GetMapping("/vehicles")
    public String listVehicles(Model model) {
        model.addAttribute("vehicles", vehicleAssetService.findAll());
        return "assets/vehicle-list";
    }

    @GetMapping("/vehicles/new")
    public String showVehicleCreateForm(Model model) {
        model.addAttribute("vehicle", new VehicleAssetDto());
        return "assets/vehicle-form";
    }

    @PostMapping("/vehicles/save")
    public String saveVehicle(@Valid @ModelAttribute("vehicle") VehicleAssetDto dto,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "assets/vehicle-form";
        }
        try {
            vehicleAssetService.create(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Vehicle created successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/assets/vehicles";
    }
}