package com.progobot.practicespringboot.repository;

import com.progobot.practicespringboot.entity.BankAccount;
import com.progobot.practicespringboot.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    public List<Transaction> findByStatusAndBankAccount(String status, BankAccount bankAccount);
    @Query(
            value = "SELECT * FROM transaction  where account_number=:accountNumber and date between :from and :to",
            nativeQuery = true
    )
    public List<Transaction> findAllBetween(@Param("from") Date from, @Param("to") Date to, @Param("accountNumber") Long accontNumber);

    public List<Transaction> findByTransactiondateBetween(Date from, Date to);
}
