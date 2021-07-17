package com.progobot.practicespringboot.validation;

import com.progobot.practicespringboot.error.AccountTypeException;
import com.progobot.practicespringboot.error.DateException;

import java.text.ParseException;

public interface Validation {
    boolean isValidDate(String date) throws DateException, ParseException;
    boolean isValidType(String accountType) throws AccountTypeException;
}
