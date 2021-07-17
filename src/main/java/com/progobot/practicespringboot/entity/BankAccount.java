package com.progobot.practicespringboot.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(
 name = "bank_account"
)
public class BankAccount implements Serializable {
    @Id
    @SequenceGenerator(
            name = "account_number_sequence",
            sequenceName = "account_number_sequence",
            initialValue = 0,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_number_sequence"
    )
    private Long accountNumber;
    @NotBlank(message = "Account type is required")
    @Column(
            name = "account_type",
            nullable = false,
            columnDefinition = "varchar(10) check(account_type='current' OR account_type='savings')"
    )
    private String accountType;
    @NotBlank(message = "Holder name is required")
    @Column(
            name = "holder_name",
            nullable = false
    )
    private String holderName;
    @Column(
            name = "dob",
            nullable = false
    )
    private Date dob;
    @Column(
            name="transaction_fee",
            nullable = false
    )
    private float transactionFee=0;
    @Column(
            name = "created_at",
            nullable = false
    )
    private Timestamp createdAt = Timestamp.from(Instant.now());
    @Column(
            name = "updated_at",
            nullable = false,
            columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"
    )
    private Timestamp updatedAt = Timestamp.from(Instant.now());
    @OneToMany(mappedBy = "bankAccount", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Transaction> transactions;
    public BankAccount() {
    }

    public BankAccount(Long accountNumber, String accountType, String holderName, Date dob,
                       float transactionFee, Timestamp createdAt, Timestamp updatedAt) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.holderName = holderName;
        this.dob = dob;
        this.transactionFee = transactionFee;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
