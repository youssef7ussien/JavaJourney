package com.project.jwallet.controllers.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.jwallet.exceptions.InsufficientBalanceException;
import com.project.jwallet.exceptions.TransactionNotFoundException;
import com.project.jwallet.exceptions.UserNotFoundException;
import com.project.jwallet.models.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleTransactionNotFoundException(TransactionNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorDetails> handleInsufficientBalanceException(InsufficientBalanceException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDetails(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGenericException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }

}