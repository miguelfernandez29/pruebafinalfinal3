package com.example.app.repository;

import com.example.app.entity.UrbanProperty;
import com.example.app.entity.UrbanPropertyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UrbanPropertyRepository extends JpaRepository<UrbanProperty, UrbanPropertyId> {

    Optional<UrbanProperty> findByPresentationYearAndTaxTypeCodeAndPresentationCodeAndCauseNifAndSubCauseCodeAndAssetSequence(
            Integer presentationYear, String taxTypeCode, String presentationCode,
            String causeNif, String subCauseCode, Integer assetSequence);

    List<UrbanProperty> findByCadastralReference(String cadastralReference);

    @Query("SELECT u FROM UrbanProperty u WHERE u.provinceCode = :provinceCode " +
           "AND u.municipalityCode = :municipalityCode")
    List<UrbanProperty> findByProvinceAndMunicipality(@Param("provinceCode") String provinceCode,
                                                       @Param("municipalityCode") String municipalityCode);
}