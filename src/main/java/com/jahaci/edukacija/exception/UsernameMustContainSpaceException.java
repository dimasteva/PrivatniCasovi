package com.jahaci.edukacija.exception;

public class UsernameMustContainSpaceException extends RuntimeException{
    public UsernameMustContainSpaceException(String message) {
        super(message);
    }
}
