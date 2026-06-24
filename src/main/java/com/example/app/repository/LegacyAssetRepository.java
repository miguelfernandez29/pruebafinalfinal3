package com.example.app.repository;

import com.example.app.entity.LegacyAsset;
import com.example.app.entity.LegacyAssetId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LegacyAssetRepository extends JpaRepository<LegacyAsset, LegacyAssetId> {

    List<LegacyAsset> findByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNifAndAssetSequence(
            Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, Integer assetSequence);

    void deleteByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNifAndAssetSequence(
            Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, Integer assetSequence);
}