package com.example.taskify.models;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ErrorDetails {

    private final int status;
    private final String message;
    private final LocalDateTime timestamp;

    public ErrorDetails(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

}

