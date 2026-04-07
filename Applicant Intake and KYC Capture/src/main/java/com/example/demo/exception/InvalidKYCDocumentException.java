package com.example.demo.exception;

public class InvalidKYCDocumentException extends RuntimeException {

    public InvalidKYCDocumentException(String message) {
        super(message);
    }
}