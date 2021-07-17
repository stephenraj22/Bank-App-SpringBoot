package com.progobot.practicespringboot.error;

import com.progobot.practicespringboot.entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@ResponseStatus
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorMessage> accountNotFoundException(AccountNotFoundException exception,
                                                                 WebRequest request){

        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(DateException.class)
    public ResponseEntity<ErrorMessage> dateException(DateException exception,
                                                                 WebRequest request){

        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(AccountTypeException.class)
    public ResponseEntity<ErrorMessage> accountTypeException(AccountTypeException exception,
                                                                 WebRequest request){

        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(BalanceException.class)
    public ResponseEntity<ErrorMessage> balanceException(BalanceException exception,
                                                                 WebRequest request){

        ErrorMessage message = new ErrorMessage(HttpStatus.PRECONDITION_FAILED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(message);
    }
}
