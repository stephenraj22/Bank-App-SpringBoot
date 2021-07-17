package com.progobot.practicespringboot.form;

import org.springframework.stereotype.Component;

@Component
public class TransactionForm {
    private float amount;
    private String type;

    public TransactionForm(float amount, String type) {
        this.amount = amount;
        this.type = type;
    }

    public TransactionForm() {
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

    @Override
    public String toString() {
        return "TransactionForm{" +
                "amount=" + amount +
                ", type='" + type + '\'' +
                '}';
    }
}
