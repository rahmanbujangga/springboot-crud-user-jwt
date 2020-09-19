package com.bujangga.springdemo.exception;

public class UserServiceException extends RuntimeException{
    private static final long serialVersionUID = 1740227365676852241L;

    public UserServiceException(String message) {
        super(message);
    }
}
