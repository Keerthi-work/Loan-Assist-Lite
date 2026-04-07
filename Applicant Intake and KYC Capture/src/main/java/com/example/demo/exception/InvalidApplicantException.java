package com.example.demo.exception;

public class InvalidApplicantException extends RuntimeException {

    public InvalidApplicantException(String message) {
        super(message);
    }
}