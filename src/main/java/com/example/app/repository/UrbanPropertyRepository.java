package com.example.app.repository;

import com.example.app.entity.UrbanProperty;
import com.example.app.entity.UrbanPropertyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrbanPropertyRepository extends JpaRepository<UrbanProperty, UrbanPropertyId> {

    List<UrbanProperty> findByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNif(
            Integer presentationYear, String taxType, String presentationCode, String taxpayerNif);
}