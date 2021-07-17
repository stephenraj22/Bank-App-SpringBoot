package com.progobot.practicespringboot.service;

import com.progobot.practicespringboot.entity.BankAccount;
import com.progobot.practicespringboot.entity.Transaction;
import com.progobot.practicespringboot.error.AccountNotFoundException;
import com.progobot.practicespringboot.error.BalanceException;
import com.progobot.practicespringboot.form.AccountStatementForm;
import com.progobot.practicespringboot.responsetemplate.BankAccountResponse;
import com.progobot.practicespringboot.responsetemplate.StatementResponse;

import java.text.ParseException;
import java.util.List;

public interface BankAccountService {
    public BankAccountResponse saveBankAccount(BankAccount bankAccount, float initialDeposit) throws ParseException, BalanceException;
    void deleteBankAccountByNumber(Long accountNumber);
    BankAccount updateBankAccountByNumber(Long accountNumber, BankAccount bankAccount) throws AccountNotFoundException;

    StatementResponse getAccountStatement(AccountStatementForm accountStatementForm, Long accountNumber);
    public BankAccount getBankAccountByNumber(Long accountNumber) throws AccountNotFoundException;
    public List<BankAccount> fetchAllBankAccounts();
    //List<BankAccount> getBankAccountsByIfsc(String ifscCode);
    //List<BankAccount> getBankAccountsByIfscIgnoreCase(String ifscCode);
}
