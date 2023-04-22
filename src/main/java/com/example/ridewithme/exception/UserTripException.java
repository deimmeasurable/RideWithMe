package com.example.ridewithme.exception;

public class UserTripException extends UserNotFoundException {
    public UserTripException(String message) {
        super(message);
    }
}
