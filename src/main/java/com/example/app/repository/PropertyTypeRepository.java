package com.example.app.repository;

import com.example.app.entity.PropertyType;
import com.example.app.entity.PropertyTypeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyTypeRepository extends JpaRepository<PropertyType, PropertyTypeId> {

    List<PropertyType> findByAssetNatureCodeAndActiveIndicator(String assetNatureCode, String activeIndicator);

    List<PropertyType> findByAssetNatureCode(String assetNatureCode);
}