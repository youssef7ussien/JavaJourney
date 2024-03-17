package com.example.taskify.controllers.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.taskify.exceptions.TaskNotFoundException;
import com.example.taskify.models.ErrorDetails;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleTaskNotFoundException(TaskNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception e) {
        System.err.println("[ERROR] handleGenericException: " + e);

        // return ResponseEntity
        // .status(HttpStatus.INTERNAL_SERVER_ERROR)
        // .body(new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
        // e.getMessage()));

        return "not-found.html";
    }

}
