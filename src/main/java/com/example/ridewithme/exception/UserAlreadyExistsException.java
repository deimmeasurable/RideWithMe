package com.example.ridewithme.exception;

public class UserAlreadyExistsException extends UserNotFoundException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
