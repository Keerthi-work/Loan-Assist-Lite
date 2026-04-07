package com.example.demo.exception;

public class KYCDocumentNotFoundException extends RuntimeException 
{
    public KYCDocumentNotFoundException(String message) {
        super(message);
    }
}