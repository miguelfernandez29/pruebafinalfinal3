package com.example.app.service;

import com.example.app.dto.GeneralDataDTO;
import com.example.app.dto.PropertyTypeDTO;
import com.example.app.dto.ProvinceDTO;
import com.example.app.dto.MunicipalityDTO;
import com.example.app.entity.GeneralData;
import com.example.app.entity.PropertyType;
import com.example.app.entity.Province;
import com.example.app.entity.Municipality;
import com.example.app.repository.GeneralDataRepository;
import com.example.app.repository.PropertyTypeRepository;
import com.example.app.repository.ProvinceRepository;
import com.example.app.repository.MunicipalityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ReferenceDataService {

    private final GeneralDataRepository generalDataRepository;
    private final PropertyTypeRepository propertyTypeRepository;
    private final ProvinceRepository provinceRepository;
    private final MunicipalityRepository municipalityRepository;

    public ReferenceDataService(GeneralDataRepository generalDataRepository,
                                PropertyTypeRepository propertyTypeRepository,
                                ProvinceRepository provinceRepository,
                                MunicipalityRepository municipalityRepository) {
        this.generalDataRepository = generalDataRepository;
        this.propertyTypeRepository = propertyTypeRepository;
        this.provinceRepository = provinceRepository;
        this.municipalityRepository = municipalityRepository;
    }

    public List<GeneralDataDTO> getActiveGeneralData(String dataType) {
        return generalDataRepository.findByDataTypeAndActiveIndicator(dataType, "S")
                .stream()
                .map(this::toGeneralDataDTO)
                .collect(Collectors.toList());
    }

    public List<PropertyTypeDTO> getActivePropertyTypes(String assetNatureCode) {
        return propertyTypeRepository.findByAssetNatureCodeAndActiveIndicator(assetNatureCode, "S")
                .stream()
                .map(this::toPropertyTypeDTO)
                .collect(Collectors.toList());
    }

    public List<ProvinceDTO> getActiveProvinces() {
        return provinceRepository.findByActiveIndicator("S")
                .stream()
                .map(this::toProvinceDTO)
                .collect(Collectors.toList());
    }

    public List<MunicipalityDTO> getActiveMunicipalities(String provinceCode) {
        return municipalityRepository.findByProvinceCodeAndActiveIndicator(provinceCode, "S")
                .stream()
                .map(this::toMunicipalityDTO)
                .collect(Collectors.toList());
    }

    private GeneralDataDTO toGeneralDataDTO(GeneralData entity) {
        GeneralDataDTO dto = new GeneralDataDTO();
        dto.setDataType(entity.getDataType());
        dto.setDataCode(entity.getDataCode());
        dto.setDescription(entity.getDescription());
        dto.setActiveIndicator(entity.getActiveIndicator());
        return dto;
    }

    private PropertyTypeDTO toPropertyTypeDTO(PropertyType entity) {
        PropertyTypeDTO dto = new PropertyTypeDTO();
        dto.setAssetNatureCode(entity.getAssetNatureCode());
        dto.setPropertyTypeCode(entity.getPropertyTypeCode());
        dto.setPropertyTypeDescription(entity.getPropertyTypeDescription());
        dto.setActiveIndicator(entity.getActiveIndicator());
        return dto;
    }

    private ProvinceDTO toProvinceDTO(Province entity) {
        ProvinceDTO dto = new ProvinceDTO();
        dto.setProvinceCode(entity.getProvinceCode());
        dto.setProvinceDescription(entity.getProvinceDescription());
        dto.setActiveIndicator(entity.getActiveIndicator());
        return dto;
    }

    private MunicipalityDTO toMunicipalityDTO(Municipality entity) {
        MunicipalityDTO dto = new MunicipalityDTO();
        dto.setProvinceCode(entity.getProvinceCode());
        dto.setMunicipalityCode(entity.getMunicipalityCode());
        dto.setMunicipalityDescription(entity.getMunicipalityDescription());
        dto.setActiveIndicator(entity.getActiveIndicator());
        return dto;
    }
}