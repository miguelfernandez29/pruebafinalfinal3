package com.example.app.repository;

import com.example.app.entity.ListedSecurities;
import com.example.app.entity.ListedSecuritiesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ListedSecuritiesRepository extends JpaRepository<ListedSecurities, ListedSecuritiesId> {

    Optional<ListedSecurities> findByPresentationYearAndTaxTypeCodeAndPresentationCodeAndCauseNifAndSubCauseCodeAndAssetSequence(
            Integer presentationYear, String taxTypeCode, String presentationCode,
            String causeNif, String subCauseCode, Integer assetSequence);

    List<ListedSecurities> findByIsinCode(String isinCode);
}