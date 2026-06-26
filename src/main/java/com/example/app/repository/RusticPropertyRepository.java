package com.example.app.repository;

import com.example.app.entity.RusticProperty;
import com.example.app.entity.RusticPropertyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RusticPropertyRepository extends JpaRepository<RusticProperty, RusticPropertyId> {

    Optional<RusticProperty> findByPresentationYearAndTaxTypeCodeAndPresentationCodeAndCauseNifAndSubCauseCodeAndAssetSequence(
            Integer presentationYear, String taxTypeCode, String presentationCode,
            String causeNif, String subCauseCode, Integer assetSequence);

    List<RusticProperty> findByCadastralReference(String cadastralReference);

    @Query("SELECT r FROM RusticProperty r WHERE r.provinceCode = :provinceCode " +
           "AND r.municipalityCode = :municipalityCode " +
           "AND r.polygon = :polygon AND r.parcel = :parcel")
    List<RusticProperty> findByLocation(@Param("provinceCode") String provinceCode,
                                         @Param("municipalityCode") String municipalityCode,
                                         @Param("polygon") String polygon,
                                         @Param("parcel") String parcel);
}