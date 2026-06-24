package com.example.app.repository;

import com.example.app.entity.AssetDocument;
import com.example.app.entity.AssetDocumentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetDocumentRepository extends JpaRepository<AssetDocument, AssetDocumentId> {

    List<AssetDocument> findByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNif(
            Integer presentationYear, String taxType, String presentationCode, String taxpayerNif);
}