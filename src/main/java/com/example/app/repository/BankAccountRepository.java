package com.example.app.repository;

import com.example.app.entity.BankAccount;
import com.example.app.entity.BankAccountId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, BankAccountId> {

    List<BankAccount> findByPresentationYearAndTaxTypeAndPresentationCodeAndTaxpayerNif(
            Integer presentationYear, String taxType, String presentationCode, String taxpayerNif);
}