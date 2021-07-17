package com.progobot.practicespringboot.responsetemplate;

import com.progobot.practicespringboot.entity.BankAccount;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

public class BankAccountResponse {
    private Long accountNumber;
    private String accountType;
    private String holderName;
    private Date dob;
    private float transactionFee;
    private float balance;
    private Timestamp createdAt ;
    private Timestamp updatedAt ;

    public BankAccountResponse(BankAccount bankAccount, float balance) {
        this.accountNumber = bankAccount.getAccountNumber();
        this.holderName = bankAccount.getHolderName();
        this.dob = bankAccount.getDob();
        this.balance = balance;
        this.transactionFee = bankAccount.getTransactionFee();
        this.accountType = bankAccount.getAccountType();
        this.createdAt = bankAccount.getCreatedAt();
        this.updatedAt = bankAccount.getUpdatedAt();
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public float getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(float transactionFee) {
        this.transactionFee = transactionFee;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
