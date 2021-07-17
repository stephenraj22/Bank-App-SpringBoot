package com.progobot.practicespringboot.service;

import com.progobot.practicespringboot.entity.BankAccount;
import com.progobot.practicespringboot.entity.Transaction;
import com.progobot.practicespringboot.error.BalanceException;
import com.progobot.practicespringboot.responsetemplate.TransactionResponse;
import org.json.JSONObject;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

public interface TransactionService {
    public TransactionResponse insertTransaction(BankAccount bankAccount, float amount, String type) throws ParseException, BalanceException;
    public List<Transaction> getAllTransactionsBetween(Date from, Date to, Long accountNumber);
}
