package com.progobot.practicespringboot.error;

public class AccountTypeException extends RuntimeException{
    public AccountTypeException() {
    }

    public AccountTypeException(String message) {
        super(message);
    }

    public AccountTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountTypeException(Throwable cause) {
        super(cause);
    }

    public AccountTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
