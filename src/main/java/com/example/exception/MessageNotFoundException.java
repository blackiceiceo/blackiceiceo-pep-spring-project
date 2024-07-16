package com.example.exception;

public class MessageNotFoundException extends RuntimeException{
    public MessageNotFoundException(){
        super("Message not found.");
    }

    public MessageNotFoundException(String message){
        super(message);
    }
}
