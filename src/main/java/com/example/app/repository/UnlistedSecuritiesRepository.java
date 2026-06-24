package com.example.app.repository;

import com.example.app.entity.UnlistedSecurities;
import com.example.app.entity.UnlistedSecuritiesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnlistedSecuritiesRepository extends JpaRepository<UnlistedSecurities, UnlistedSecuritiesId> {

    List<UnlistedSecurities> findByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNif(
            Integer presentationYear, String taxType, String presentationCode, String taxpayerNif);
}