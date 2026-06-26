package com.example.app.repository;

import com.example.app.entity.BankAccountAsset;
import com.example.app.entity.BankAccountAssetId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountAssetRepository extends JpaRepository<BankAccountAsset, BankAccountAssetId> {

    Optional<BankAccountAsset> findByPresentationYearAndTaxTypeCodeAndPresentationCodeAndCauseNifAndSubCauseCodeAndAssetSequence(
            Integer presentationYear, String taxTypeCode, String presentationCode,
            String causeNif, String subCauseCode, Integer assetSequence);

    List<BankAccountAsset> findByDepositAccount(String depositAccount);
}