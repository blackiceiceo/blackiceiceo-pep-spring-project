package com.example.exception;

public class AccountCreationException extends RuntimeException{
    public AccountCreationException(){
        super("Account unable to make.");
    }

    public AccountCreationException(String message){
        super(message);
    }
}
