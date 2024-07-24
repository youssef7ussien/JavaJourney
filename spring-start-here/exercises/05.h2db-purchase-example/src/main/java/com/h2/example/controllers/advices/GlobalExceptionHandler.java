package com.h2.example.controllers.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.h2.example.exceptions.ControllerException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ControllerException.class)
    @ResponseBody
    public ResponseEntity<String> handleControllerException(ControllerException e) {
        e.printStackTrace();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("[ERROR] Internal Server Error");
    }
}