package com.example.app.repository;

import com.example.app.entity.BusinessAsset;
import com.example.app.entity.BusinessAssetId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessAssetRepository extends JpaRepository<BusinessAsset, BusinessAssetId> {

    Optional<BusinessAsset> findByPresentationYearAndTaxTypeCodeAndPresentationCodeAndCauseNifAndSubCauseCodeAndAssetSequence(
            Integer presentationYear, String taxTypeCode, String presentationCode,
            String causeNif, String subCauseCode, Integer assetSequence);

    List<BusinessAsset> findByCompanyNif(String companyNif);

    @Query("SELECT b FROM BusinessAsset b WHERE b.businessAffectedIndicator = 'S'")
    List<BusinessAsset> findBusinessAffectedAssets();
}