package com.progobot.practicespringboot.responsetemplate;

import java.sql.Date;
import java.util.List;

public class StatementResponse {
    private Long accountNumber;
    private String accountType;
    private String holderName;
    private Date dob;
    private float balance;
    private Date from_date;
    private Date to_date;
    private List<StatementTransaction> transactionResponseList;

    public StatementResponse() {
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

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    public List<StatementTransaction> getTransactionResponseList() {
        return transactionResponseList;
    }

    public void setTransactionResponseList(List<StatementTransaction> transactionResponseList) {
        this.transactionResponseList = transactionResponseList;
    }

    public StatementResponse(Long accountNumber, String accountType, String holderName, Date dob, float balance, Date from_date, Date to_date, List<StatementTransaction> transactionResponseList) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.holderName = holderName;
        this.dob = dob;
        this.balance = balance;
        this.from_date = from_date;
        this.to_date = to_date;
        this.transactionResponseList = transactionResponseList;
    }


}
