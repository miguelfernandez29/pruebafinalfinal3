package com.example.app.repository;

import com.example.app.entity.UnlistedSecurities;
import com.example.app.entity.UnlistedSecuritiesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnlistedSecuritiesRepository extends JpaRepository<UnlistedSecurities, UnlistedSecuritiesId> {

    Optional<UnlistedSecurities> findByPresentationYearAndTaxTypeCodeAndPresentationCodeAndCauseNifAndSubCauseCodeAndAssetSequence(
            Integer presentationYear, String taxTypeCode, String presentationCode,
            String causeNif, String subCauseCode, Integer assetSequence);

    List<UnlistedSecurities> findByCompanyCif(String companyCif);
}