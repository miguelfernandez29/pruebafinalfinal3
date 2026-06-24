package com.example.app.repository;

import com.example.app.entity.ListedSecurities;
import com.example.app.entity.ListedSecuritiesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListedSecuritiesRepository extends JpaRepository<ListedSecurities, ListedSecuritiesId> {

    List<ListedSecurities> findByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNif(
            Integer presentationYear, String taxType, String presentationCode, String taxpayerNif);
}