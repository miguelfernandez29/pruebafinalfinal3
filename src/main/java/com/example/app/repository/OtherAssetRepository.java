package com.example.app.repository;

import com.example.app.entity.OtherAsset;
import com.example.app.entity.OtherAssetId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OtherAssetRepository extends JpaRepository<OtherAsset, OtherAssetId> {

    List<OtherAsset> findByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNif(
            Integer presentationYear, String taxType, String presentationCode, String taxpayerNif);
}