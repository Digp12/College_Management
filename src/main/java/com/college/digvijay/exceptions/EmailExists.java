package com.college.digvijay.exceptions;

public class EmailExists extends RuntimeException {
    public EmailExists(String message) {
        super(message);
    }
}
