package com.progobot.practicespringboot.validation;

import com.progobot.practicespringboot.error.AccountTypeException;
import com.progobot.practicespringboot.error.DateException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ValidationImp implements Validation{
    @Override
    public boolean isValidDate(String date) throws DateException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        if(sdf.parse(date, new ParsePosition(0))!= null){
            if(isPastDate(sdf, date))
                return true;
        }
        throw new DateException("Invalid Date");
    }

    @Override
    public boolean isValidType(String accountType) throws AccountTypeException {
        if(accountType.equals("current") || accountType.equals("savings"))
            return true;
        throw new AccountTypeException("Account type should be 'current' or 'savings'");
    }

    public boolean isPastDate(SimpleDateFormat format, String date) throws ParseException, DateException {
        Date current = new Date();
        Date dob = format.parse(date);
        if(dob.before(current)) {
            //long years = (((current.getTime() - dob.getTime())  / (1000 * 60 * 60 * 24)) % 365);
            if( current.getYear()- dob.getYear() < 100){
                return true;
            }
            else
                throw new DateException("100+ years");

        }
        throw new DateException("Past Date Required");
    }



}
