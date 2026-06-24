package com.example.app.repository;

import com.example.app.entity.GeneralData;
import com.example.app.entity.GeneralDataId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneralDataRepository extends JpaRepository<GeneralData, GeneralDataId> {

    List<GeneralData> findByDataTypeAndActiveIndicator(String dataType, String activeIndicator);

    List<GeneralData> findByDataType(String dataType);
}