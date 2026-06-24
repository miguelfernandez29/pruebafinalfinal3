package com.example.app.repository;

import com.example.app.entity.BusinessAsset;
import com.example.app.entity.BusinessAssetId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessAssetRepository extends JpaRepository<BusinessAsset, BusinessAssetId> {

    List<BusinessAsset> findByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNif(
            Integer presentationYear, String taxType, String presentationCode, String taxpayerNif);
}