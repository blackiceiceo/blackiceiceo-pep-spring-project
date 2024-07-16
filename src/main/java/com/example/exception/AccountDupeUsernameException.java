package com.example.exception;

public class AccountDupeUsernameException extends RuntimeException{
    public AccountDupeUsernameException(){
        super("Username is duped.");
    }

    public AccountDupeUsernameException(String message){
        super(message);
    }
}
