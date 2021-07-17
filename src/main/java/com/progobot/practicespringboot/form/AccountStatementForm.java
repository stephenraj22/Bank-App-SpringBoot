package com.progobot.practicespringboot.form;

import java.sql.Date;

public class AccountStatementForm {
    private String from = "";
    private String to;

    public AccountStatementForm( String from, String to) {
        this.from = from;
        this.to = to;
    }

    public AccountStatementForm() {
    }





    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "AccountStatementForm{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
