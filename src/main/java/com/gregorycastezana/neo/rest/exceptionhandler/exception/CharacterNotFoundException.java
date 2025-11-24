package com.gregorycastezana.neo.rest.exceptionhandler.exception;

public class CharacterNotFoundException extends RuntimeException {
    public CharacterNotFoundException(String message) {
        super(message);
    }
}
