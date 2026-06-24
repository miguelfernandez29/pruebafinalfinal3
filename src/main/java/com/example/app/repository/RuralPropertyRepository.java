package com.example.app.repository;

import com.example.app.entity.RuralProperty;
import com.example.app.entity.RuralPropertyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuralPropertyRepository extends JpaRepository<RuralProperty, RuralPropertyId> {

    List<RuralProperty> findByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNif(
            Integer presentationYear, String taxType, String presentationCode, String taxpayerNif);
}