package com.gregorycastezana.neo.rest.exceptionhandler;

import lombok.Data;

@Data
public class ErrorResponse {

    String field;
    String message;

    public ErrorResponse() {}

    public ErrorResponse(String message) {
        this.message = message;
    }
}
