package com.example.exception;

public class MessageCreationException extends RuntimeException{
    public MessageCreationException() {
        super("Message unable to be added.");
    }

    public MessageCreationException(String message) {
        super(message);
    }
}
