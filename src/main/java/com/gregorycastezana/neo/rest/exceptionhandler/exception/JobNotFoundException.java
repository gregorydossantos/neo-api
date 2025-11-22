package com.gregorycastezana.neo.rest.exceptionhandler.exception;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException(String message) {
        super(message);
    }
}
