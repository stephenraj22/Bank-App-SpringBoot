package com.progobot.practicespringboot.responsetemplate;

import com.progobot.practicespringboot.entity.Transaction;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
@Component
public class StatementTransaction {
    private Long transactionId;
    private float amount;
    private String type;
    private Date transactiondate;
    private String status ;
    private Timestamp createdAt ;
    private Timestamp updatedAt ;
    private float oldBalance;
    private float newBalance;

    public StatementTransaction(Transaction transaction, float oldBalance, float newBalance) {
        this.transactionId = transaction.getTransactionId();
        this.amount = transaction.getAmount();
        this.type = transaction.getType();
        this.oldBalance = oldBalance;
        this.newBalance = newBalance;
        this.transactiondate = transaction.getTransactiondate();
        this.status = transaction.getStatus();
        this.createdAt = transaction.getCreatedAt();
        this.updatedAt = transaction.getUpdatedAt();
    }
    public StatementTransaction() {
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

    public void setTransactiondate(Date transactiondate) {
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

    public float getOldBalance() {
        return oldBalance;
    }

    public void setOldBalance(float oldBalance) {
        this.oldBalance = oldBalance;
    }

    public float getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(float newBalance) {
        this.newBalance = newBalance;
    }
}
