package com.example.app.repository;

import com.example.app.entity.AssetCause;
import com.example.app.entity.AssetCauseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetCauseRepository extends JpaRepository<AssetCause, AssetCauseId> {

    List<AssetCause> findByPresentationYearAndTaxTypeCodeAndPresentationCodeAndCauseNifAndSubCauseCode(
            Integer presentationYear, String taxTypeCode, String presentationCode,
            String causeNif, String subCauseCode);

    @Query("SELECT COALESCE(MAX(a.assetSequence), 0) + 1 FROM AssetCause a " +
           "WHERE a.presentationYear = :presentationYear " +
           "AND a.taxTypeCode = :taxTypeCode " +
           "AND a.presentationCode = :presentationCode " +
           "AND a.causeNif = :causeNif " +
           "AND a.subCauseCode = :subCauseCode")
    Integer findNextAssetSequence(@Param("presentationYear") Integer presentationYear,
                                  @Param("taxTypeCode") String taxTypeCode,
                                  @Param("presentationCode") String presentationCode,
                                  @Param("causeNif") String causeNif,
                                  @Param("subCauseCode") String subCauseCode);

    List<AssetCause> findByPresentationYearAndTaxTypeCodeAndPresentationCodeAndCauseNifAndSubCauseCodeAndAssetNatureCode(
            Integer presentationYear, String taxTypeCode, String presentationCode,
            String causeNif, String subCauseCode, String assetNatureCode);

    @Query("SELECT a FROM AssetCause a WHERE a.presentationYear = :presentationYear " +
           "AND a.taxTypeCode = :taxTypeCode AND a.presentationCode = :presentationCode " +
           "AND a.causeNif = :causeNif AND a.subCauseCode = :subCauseCode " +
           "AND a.reductionIndicator = 'S'")
    List<AssetCause> findAssetsWithReductions(@Param("presentationYear") Integer presentationYear,
                                              @Param("taxTypeCode") String taxTypeCode,
                                              @Param("presentationCode") String presentationCode,
                                              @Param("causeNif") String causeNif,
                                              @Param("subCauseCode") String subCauseCode);
}