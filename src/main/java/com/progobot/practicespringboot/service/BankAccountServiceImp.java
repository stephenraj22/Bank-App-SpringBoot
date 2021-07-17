package com.progobot.practicespringboot.service;

import com.progobot.practicespringboot.entity.BankAccount;
import com.progobot.practicespringboot.entity.Transaction;
import com.progobot.practicespringboot.error.AccountNotFoundException;
import com.progobot.practicespringboot.error.BalanceException;
import com.progobot.practicespringboot.form.AccountStatementForm;
import com.progobot.practicespringboot.repository.BankAccountRepository;
import com.progobot.practicespringboot.responsetemplate.BankAccountResponse;
import com.progobot.practicespringboot.responsetemplate.StatementResponse;
import com.progobot.practicespringboot.responsetemplate.StatementTransaction;
import com.progobot.practicespringboot.responsetemplate.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class BankAccountServiceImp implements BankAccountService{

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private TransactionService transactionService;
    @Override
    public BankAccountResponse saveBankAccount(BankAccount bankAccount, float initialDeposit) throws ParseException, BalanceException {
        float balance = 0;
        if(bankAccount.getAccountType().equals("current")){
            bankAccount.setTransactionFee(5F);
        }
        BankAccount newBankAccount =  bankAccountRepository.save(bankAccount);
        if(initialDeposit>0){
            TransactionResponse transactionResponse = transactionService.insertTransaction(newBankAccount, initialDeposit, "deposit");
            balance = transactionResponse.getNewBalance();
        }

        return new BankAccountResponse(bankAccount, balance);
    }

    @Override
    public List<BankAccount> fetchAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    @Override
    public BankAccount getBankAccountByNumber(Long accountNumber) throws AccountNotFoundException {
        //it returns optional hence get() should be used
        Optional<BankAccount> bankAccountOptional = bankAccountRepository.findById(accountNumber);
        if(!bankAccountOptional.isPresent()){
            throw new AccountNotFoundException("Account not found");
        }
        return bankAccountOptional.get();
    }

    @Override
    public void deleteBankAccountByNumber(Long accountNumber) {
        bankAccountRepository.deleteById(accountNumber);
    }

    @Override
    public BankAccount updateBankAccountByNumber(Long accountNumber, BankAccount bankAccount) throws AccountNotFoundException {
        Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(accountNumber);
        if(optionalBankAccount.isPresent()){
            BankAccount bankAccount1 = optionalBankAccount.get();
            if(!bankAccount.getAccountType().equals(bankAccount1.getAccountType())){
                bankAccount1.setAccountType(bankAccount.getAccountType());
                if(bankAccount.getAccountType().equals("current")){
                    bankAccount1.setTransactionFee(5F);
                }
                else {
                    bankAccount1.setTransactionFee(0F);
                }
            }
            bankAccount1.setHolderName(bankAccount.getHolderName());
            bankAccount1.setDob(bankAccount.getDob());
            bankAccount1.setUpdatedAt(Timestamp.from(Instant.now()));
            return bankAccountRepository.save(bankAccount1);
        }
        throw new AccountNotFoundException("Account Not Found");

    }

    @Override
    public StatementResponse getAccountStatement(AccountStatementForm accountStatementForm, Long accountNumber) {
        Date fromDate,toDate, dob;
        toDate = Date.valueOf(accountStatementForm.getTo());
        float newBalance, oldBalance, balance = 0;
        BankAccount bankAccount = bankAccountRepository.getById(accountNumber);
        dob = new Date(bankAccount.getDob().getTime());
        if(accountStatementForm.getFrom().equals("")){
            fromDate = Date.valueOf(bankAccount.getCreatedAt().toLocalDateTime().toLocalDate());
        }else {
            fromDate = Date.valueOf(accountStatementForm.getFrom());
        }
        List<Transaction> transactions = transactionService.getAllTransactionsBetween(
                fromDate,
                toDate,
                accountNumber
        );
        List<StatementTransaction> transactionResponseList = new ArrayList<StatementTransaction>();
        for(Transaction transaction:transactions){
            oldBalance = balance;
            if(transaction.getStatus().equals("SUCCESS")){
                if(transaction.getType().equals("withdrawal")){
                    balance -= transaction.getAmount()+bankAccount.getTransactionFee();
                }
                else {
                    balance += transaction.getAmount()-bankAccount.getTransactionFee();
                }
            }
            newBalance = balance;
            StatementTransaction transactionResponse = new StatementTransaction(
                transaction,
                oldBalance,
                newBalance
            );
            transactionResponseList.add(transactionResponse);
        }

        System.out.println(transactions);
        return new StatementResponse(
            bankAccount.getAccountNumber(),
                bankAccount.getAccountType(),
                bankAccount.getHolderName(),
                dob,
                balance,
                fromDate,
                toDate,
                transactionResponseList
        );
    }

//    @Override
//    public List<BankAccount> getBankAccountsByIfsc(String ifscCode) {
//        return bankAccountRepository.findByifscCode(ifscCode);
//    }
}
