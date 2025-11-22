package com.gregorycastezana.neo.rest.exceptionhandler.exception;

public class CharacterNameSizeException extends RuntimeException {
    public CharacterNameSizeException(String message) {
        super(message);
    }
}
