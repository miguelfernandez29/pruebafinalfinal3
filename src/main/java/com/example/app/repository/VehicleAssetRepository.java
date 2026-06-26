package com.example.app.repository;

import com.example.app.entity.VehicleAsset;
import com.example.app.entity.VehicleAssetId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleAssetRepository extends JpaRepository<VehicleAsset, VehicleAssetId> {

    Optional<VehicleAsset> findByPresentationYearAndTaxTypeCodeAndPresentationCodeAndCauseNifAndSubCauseCodeAndAssetSequence(
            Integer presentationYear, String taxTypeCode, String presentationCode,
            String causeNif, String subCauseCode, Integer assetSequence);

    Optional<VehicleAsset> findByLicensePlate(String licensePlate);

    @Query("SELECT v FROM VehicleAsset v WHERE v.vehicleTypeCode = :vehicleTypeCode " +
           "AND v.vehicleBrandCode = :vehicleBrandCode")
    List<VehicleAsset> findByTypeAndBrand(@Param("vehicleTypeCode") String vehicleTypeCode,
                                           @Param("vehicleBrandCode") String vehicleBrandCode);
}