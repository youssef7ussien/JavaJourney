package com.h2.example.exceptions;

public class ControllerException extends RuntimeException {
    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(String message, Throwable cause) {
        super(message, cause);
    }
}
