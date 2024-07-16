package com.example.exception;

public class AccountMisMatchException extends RuntimeException{
    public AccountMisMatchException() {
        super("Account not found.");
    }

    public AccountMisMatchException(String message) {
        super(message);
    }
}
