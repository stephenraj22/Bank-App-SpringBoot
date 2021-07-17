package com.progobot.practicespringboot.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.progobot.practicespringboot.entity.BankAccount;
import com.progobot.practicespringboot.entity.Transaction;
import com.progobot.practicespringboot.error.BalanceException;
import com.progobot.practicespringboot.repository.TransactionRepository;
import com.progobot.practicespringboot.responsetemplate.TransactionResponse;
import com.progobot.practicespringboot.responsetemplate.TransactionResponseCurrent;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

@Service
public class TransactionServiceImp implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public TransactionResponse insertTransaction(BankAccount bankAccount, float amount, String type) throws ParseException, BalanceException {
        float balance = 0, oldBalance = 0, newBalance = 0,amountCopy = amount;
        TransactionResponse transactionResponse = null;
        if(bankAccount.getAccountType().equals("current"))
            if(type.equals("deposit"))
                amount -= 5;
            if(type.equals("withdrawal"))
                amount += 5;

        Transaction newTransaction = new Transaction();
        newTransaction.setBankAccount(bankAccount);
        newTransaction.setTransactiondate();
        newTransaction.setType(type);
        newTransaction.setAmount(amountCopy);
        List<Transaction> transactionList = transactionRepository.findByStatusAndBankAccount("SUCCESS", bankAccount);
        for(Transaction transaction:transactionList){
            if(transaction.getType().equals("deposit"))
                balance += transaction.getAmount()-bankAccount.getTransactionFee();

            else
                balance -= transaction.getAmount()+bankAccount.getTransactionFee();
        }
        oldBalance = balance;
        if(type.equals("withdrawal")){
            if(balance >= amount){
                newTransaction.setStatus("SUCCESS");
                newBalance = balance - amount;
            }
            else{
                throw  new BalanceException("Insufficient Balance");
            }
        }
        else{
            newTransaction.setStatus("SUCCESS");
            newBalance = balance + amount;
        }
        System.out.println(newTransaction.getType()+newTransaction.getStatus());
        newTransaction = transactionRepository.save(newTransaction);
        if(bankAccount.getAccountType().equals("current"))
            transactionResponse = new TransactionResponseCurrent(newTransaction, oldBalance, newBalance);
        else
            transactionResponse = new TransactionResponse(newTransaction,oldBalance,newBalance);
        return transactionResponse;
    }

    @Override
    public List<Transaction> getAllTransactionsBetween(Date from, Date to, Long accountNumber) {
        return transactionRepository.findAllBetween(from, to, accountNumber);
        //return transactionRepository.findByTransactiondateBetween(from, to);
    }

}
