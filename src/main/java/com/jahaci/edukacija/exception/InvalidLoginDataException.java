package com.jahaci.edukacija.exception;

public class InvalidLoginDataException extends RuntimeException{
    public InvalidLoginDataException(String message) {
        super(message);
    }
}
