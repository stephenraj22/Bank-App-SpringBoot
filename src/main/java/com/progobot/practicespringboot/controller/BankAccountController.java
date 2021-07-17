package com.progobot.practicespringboot.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.progobot.practicespringboot.entity.BankAccount;
import com.progobot.practicespringboot.entity.Transaction;
import com.progobot.practicespringboot.error.AccountTypeException;
import com.progobot.practicespringboot.error.BalanceException;
import com.progobot.practicespringboot.error.DateException;
import com.progobot.practicespringboot.form.AccountStatementForm;
import com.progobot.practicespringboot.form.BankAccountForm;
import com.progobot.practicespringboot.error.AccountNotFoundException;
import com.progobot.practicespringboot.form.TransactionForm;
import com.progobot.practicespringboot.responsetemplate.BankAccountResponse;
import com.progobot.practicespringboot.responsetemplate.StatementResponse;
import com.progobot.practicespringboot.responsetemplate.TransactionResponse;
import com.progobot.practicespringboot.service.BankAccountService;
import com.progobot.practicespringboot.service.TransactionService;
import com.progobot.practicespringboot.validation.Validation;
import org.json.JSONObject;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class BankAccountController {
    @Autowired
    private BankAccountService bankAccountService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private Validation validation;
    private final Logger LOGGER = Logger.getLogger(BankAccountController.class.getName());
    @PostMapping("/accounts")
    public BankAccountResponse saveBankAccount(@Valid @RequestBody BankAccountForm bankAccountForm) throws ParseException, DateException, AccountTypeException, BalanceException {
        boolean result = validation.isValidDate(bankAccountForm.getDob()) && validation.isValidType(bankAccountForm.getAccountType());
        System.out.println(bankAccountForm.getDob()+result);
        BankAccount bankAccount = new BankAccount(
                bankAccountForm.getAccountNumber(),
                bankAccountForm.getAccountType(),
                bankAccountForm.getHolderName(),
                Date.valueOf(bankAccountForm.getDob()),
                bankAccountForm.getTransactionFee(),
                bankAccountForm.getCreatedAt(),
                bankAccountForm.getUpdatedAt()
        );

        LOGGER.info("Save a bank account"+bankAccountForm.toString());
        return  bankAccountService.saveBankAccount(bankAccount, bankAccountForm.getInitialDeposit());
    }

    @GetMapping("/accounts/{accountNumber}")
    public BankAccount getAccountByNumber(@PathVariable("accountNumber") Long accountNumber) throws AccountNotFoundException {
        LOGGER.info("Get a bank account request:"+"/accounts/"+accountNumber);
        return bankAccountService.getBankAccountByNumber(accountNumber);
    }

    @PutMapping("/accounts/{accountNumber}")
    public BankAccount updateAccountByNumber(@PathVariable("accountNumber") Long accountNumber,
                                       @Valid @RequestBody BankAccountForm bankAccountForm) throws AccountNotFoundException, DateException, ParseException, AccountTypeException {
        LOGGER.info("Update a bank account /accounts/"+accountNumber+bankAccountForm.toString());
        boolean result = validation.isValidDate(bankAccountForm.getDob()) && validation.isValidType(bankAccountForm.getAccountType());
        System.out.println(bankAccountForm.getDob()+result);
        BankAccount bankAccount = new BankAccount(
                bankAccountForm.getAccountNumber(),
                bankAccountForm.getAccountType(),
                bankAccountForm.getHolderName(),
                Date.valueOf(bankAccountForm.getDob()),
                bankAccountForm.getTransactionFee(),
                bankAccountForm.getCreatedAt(),
                bankAccountForm.getUpdatedAt()
        );
        return bankAccountService.updateBankAccountByNumber(accountNumber, bankAccount);

    }
    @PostMapping("/transaction/{accountNumber}")
    public TransactionResponse transact(@PathVariable("accountNumber") Long accountNumber,
                                        @Valid @RequestBody TransactionForm transactionForm) throws AccountNotFoundException, ParseException, BalanceException {
        LOGGER.info("Transaction /transaction/"+accountNumber+transactionForm.toString());
        BankAccount bankAccount = bankAccountService.getBankAccountByNumber(accountNumber);
        return transactionService.insertTransaction(bankAccount, transactionForm.getAmount(), transactionForm.getType());
    }

    @PostMapping("/accounts/statement/{accountNumber}")
    public StatementResponse getStatement(@PathVariable("accountNumber") Long accountNumber,
                                          @RequestParam(value = "from",required = false) String from, @RequestParam("to") String to){
        AccountStatementForm accountStatementForm = new AccountStatementForm(from,to);
        LOGGER.info("Bank account statement /account/statement/"+accountNumber+accountStatementForm.toString());

        return bankAccountService.getAccountStatement(accountStatementForm, accountNumber);
    }
    @GetMapping("/accounts")
    public List<BankAccount> fetchAllAccounts(){
        return bankAccountService.fetchAllBankAccounts();
    }

//    @DeleteMapping("/accounts/{accountNumber}")
//    public String deleteAccountByNumber(@PathVariable("accountNumber") Long accountNumber){
//        LOGGER.info("/accounts/"+accountNumber);
//        bankAccountService.deleteBankAccountByNumber(accountNumber);
//        return "Deleted";
//    }

//    @GetMapping("/accounts/ifsccode/{ifscCode}")
//    public List<BankAccount> getAccountsByIfsc(@PathVariable("ifscCode") String ifscCode){
//        LOGGER.info("/accounts/ifsccode/"+ifscCode);
//        return bankAccountService.getBankAccountsByIfsc(ifscCode);
//    }
}
