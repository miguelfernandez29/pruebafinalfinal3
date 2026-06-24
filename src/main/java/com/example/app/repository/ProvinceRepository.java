package com.example.app.repository;

import com.example.app.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, String> {

    List<Province> findByActiveIndicator(String activeIndicator);
}