package com.example.exception;

public class MessageUpdateException extends RuntimeException{
    public MessageUpdateException(){
        super("Message cannot be updated.");
    }

    public MessageUpdateException(String message){
        super(message);
    }
}
