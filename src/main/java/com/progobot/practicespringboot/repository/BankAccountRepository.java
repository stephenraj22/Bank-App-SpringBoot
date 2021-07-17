package com.progobot.practicespringboot.repository;

import com.progobot.practicespringboot.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    //public List<BankAccount> findByifscCode(String ifscCode); for custom querying
}
