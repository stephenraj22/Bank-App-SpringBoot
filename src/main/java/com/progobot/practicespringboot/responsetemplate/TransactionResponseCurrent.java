package com.progobot.practicespringboot.responsetemplate;

import com.progobot.practicespringboot.entity.Transaction;

public class TransactionResponseCurrent extends  TransactionResponse{
    private float transactionFee = 5;

    public TransactionResponseCurrent(Transaction transaction, float oldBalance, float newBalance) {
        super(transaction, oldBalance, newBalance);
    }

    public TransactionResponseCurrent() {
    }

    public float getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(float transactionFee) {
        this.transactionFee = transactionFee;
    }
}
