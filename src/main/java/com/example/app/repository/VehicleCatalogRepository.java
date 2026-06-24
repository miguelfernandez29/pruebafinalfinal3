package com.example.app.repository;

import com.example.app.entity.VehicleCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface VehicleCatalogRepository extends JpaRepository<VehicleCatalog, Date> {

    @Query("SELECT vc FROM VehicleCatalog vc WHERE vc.catalogDate <= :registrationDate ORDER BY vc.catalogDate DESC")
    Optional<VehicleCatalog> findApplicableCatalog(@Param("registrationDate") Date registrationDate);
}
