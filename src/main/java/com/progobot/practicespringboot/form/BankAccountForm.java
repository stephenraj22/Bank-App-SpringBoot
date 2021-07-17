package com.progobot.practicespringboot.form;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Component
public class BankAccountForm {

    private Long accountNumber;

    private String accountType;

    private String holderName;
    private String dob;
    private float transactionFee=0;
    private float initialDeposit=0;

    private Timestamp createdAt = Timestamp.from(Instant.now());

    private Timestamp updatedAt = Timestamp.from(Instant.now());

    public BankAccountForm() {
    }

    public BankAccountForm(Long accountNumber, String accountType, String holderName, String dob,
                       float transactionFee, Timestamp createdAt, Timestamp updatedAt, float initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.holderName = holderName;
        this.dob = dob;
        this.transactionFee = transactionFee;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.initialDeposit = initialDeposit;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public float getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(float transactionFee) {
        this.transactionFee = transactionFee;
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

    public float getInitialDeposit() {
        return initialDeposit;
    }

    public void setInitialDeposit(float initialDeposit) {
        this.initialDeposit = initialDeposit;
    }

    @Override
    public String toString() {
        return "BankAccountForm{" +
                "accountNumber=" + accountNumber +
                ", accountType='" + accountType + '\'' +
                ", holderName='" + holderName + '\'' +
                ", dob='" + dob + '\'' +
                ", transactionFee=" + transactionFee +
                ", initialDeposit=" + initialDeposit +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
