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

    List<AssetCause> findByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNif(
            Integer presentationYear, String taxType, String presentationCode, String taxpayerNif);

    @Query("SELECT COALESCE(MAX(a.assetSequence), 0) + 1 FROM AssetCause a " +
           "WHERE a.presentationYear = :presentationYear AND a.taxType = :taxType " +
           "AND a.presentationCode = :presentationCode AND a.taxpayerNif = :taxpayerNif")
    Integer findNextAssetSequence(@Param("presentationYear") Integer presentationYear,
                                  @Param("taxType") String taxType,
                                  @Param("presentationCode") String presentationCode,
                                  @Param("taxpayerNif") String taxpayerNif);
}