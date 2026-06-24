package com.example.app.repository;

import com.example.app.entity.StreetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StreetTypeRepository extends JpaRepository<StreetType, String> {

    List<StreetType> findByActiveIndicator(String activeIndicator);
}