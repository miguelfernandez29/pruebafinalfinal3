package com.example.app.repository;

import com.example.app.entity.AssetReduction;
import com.example.app.entity.AssetReductionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetReductionRepository extends JpaRepository<AssetReduction, AssetReductionId> {

    List<AssetReduction> findByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNifAndAssetSequence(
            Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, Integer assetSequence);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM AssetReduction a " +
           "WHERE a.presentationYear = :presentationYear AND a.taxType = :taxType " +
           "AND a.presentationCode = :presentationCode AND a.taxpayerNif = :taxpayerNif " +
           "AND a.assetSequence = :assetSequence")
    boolean existsReductionForAsset(@Param("presentationYear") Integer presentationYear,
                                    @Param("taxType") String taxType,
                                    @Param("presentationCode") String presentationCode,
                                    @Param("taxpayerNif") String taxpayerNif,
                                    @Param("assetSequence") Integer assetSequence);

    void deleteByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNifAndAssetSequence(
            Integer presentationYear, String taxType, String presentationCode, String taxpayerNif, Integer assetSequence);
}