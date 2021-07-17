package com.progobot.practicespringboot.entity;

import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.text.ParseException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;

@Entity
@Table(
        name = "transaction"
)
public class Transaction implements Serializable {
    @Id
    @SequenceGenerator(
            name = "transaction_sequence",
            sequenceName = "transaction_sequence",
            initialValue = 0,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_sequence"
    )
    private Long transactionId;
    @Column(
            name="amount",
            nullable = false
    )
    private float amount;
    @Column(
            name="type",
            nullable = false,
            columnDefinition = "varchar(15) check(type='deposit' OR type='withdrawal')"
    )
    private String type;
    @Column(
            name = "date",
            nullable = false
    )
    private Date transactiondate;
    @Column(
            name="status",
            nullable = false,
            columnDefinition = "varchar(15) check(status='SUCCESS' OR status='FAILURE')"
    )
    private String status = "FAILURE";
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_number", nullable = false)
    private BankAccount bankAccount;

    public Transaction(Long transactionId, float amount, String type, Date transactiondate, String status, Timestamp createdAt, Timestamp updatedAt, BankAccount bankAccount) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.type = type;
        this.transactiondate = transactiondate;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.bankAccount = bankAccount;
    }

    public Transaction() {
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTransactiondate() {

        return transactiondate;
    }

    public void setTransactiondate() throws ParseException {
        String pattern = "yyyy-MM-dd";
        this.transactiondate = new Date(System.currentTimeMillis());
        this.transactiondate = transactiondate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }


}
