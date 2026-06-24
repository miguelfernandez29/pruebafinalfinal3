package com.example.app.repository;

import com.example.app.entity.VehicleModel;
import com.example.app.entity.VehicleModelId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleModelRepository extends JpaRepository<VehicleModel, VehicleModelId> {
}
