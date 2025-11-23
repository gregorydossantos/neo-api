package com.gregorycastezana.neo.rest.exceptionhandler.exception;

public class DetailsNotFoundException extends RuntimeException {
    public DetailsNotFoundException(String message) {
        super(message);
    }
}
