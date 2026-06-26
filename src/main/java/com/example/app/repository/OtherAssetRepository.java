package com.example.app.repository;

import com.example.app.entity.OtherAsset;
import com.example.app.entity.OtherAssetId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtherAssetRepository extends JpaRepository<OtherAsset, OtherAssetId> {

    Optional<OtherAsset> findByPresentationYearAndTaxTypeCodeAndPresentationCodeAndCauseNifAndSubCauseCodeAndAssetSequence(
            Integer presentationYear, String taxTypeCode, String presentationCode,
            String causeNif, String subCauseCode, Integer assetSequence);
}