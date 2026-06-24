package com.example.app.repository;

import com.example.app.entity.VehicleType;
import com.example.app.entity.VehicleTypeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, VehicleTypeId> {

    List<VehicleType> findByCatalogDate(Date catalogDate);
}