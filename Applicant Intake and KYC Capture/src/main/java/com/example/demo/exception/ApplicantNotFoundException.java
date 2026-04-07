package com.example.demo.exception;

public class ApplicantNotFoundException extends RuntimeException 
{
    public ApplicantNotFoundException(String message) {
        super(message);
    }
}