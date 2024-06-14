package com.javarush.exception;

public class InvalidQuestStateException extends RuntimeException {
    public InvalidQuestStateException(String message) {
        super(message);
    }
}
