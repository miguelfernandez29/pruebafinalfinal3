package com.example.app.repository;

import com.example.app.entity.Vehicle;
import com.example.app.entity.VehicleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, VehicleId> {

    List<Vehicle> findByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNif(
            Integer presentationYear, String taxType, String presentationCode, String taxpayerNif);
}