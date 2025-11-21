package com.gregorycastezana.neo.rest.exceptionhandler;

import com.gregorycastezana.neo.rest.exceptionhandler.exception.CharacterDataIntegrityException;
import com.gregorycastezana.neo.rest.exceptionhandler.exception.CharacterNameException;
import com.gregorycastezana.neo.rest.exceptionhandler.exception.CharacterNameSizeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.gregorycastezana.neo.domain.message.CommonsMessages.CHARACTER_ALREADY_REGISTER;
import static com.gregorycastezana.neo.domain.message.CommonsMessages.INVALID_CHARACTER_NAME;
import static com.gregorycastezana.neo.domain.message.CommonsMessages.SIZE_NAME_INVALID;

@ControllerAdvice
public class GameExceptionHandler {

    @ExceptionHandler(CharacterDataIntegrityException.class)
    public ResponseEntity<ErrorResponse> characterDataIntegrity(final CharacterDataIntegrityException ex) {
        ErrorResponse error = new ErrorResponse(CHARACTER_ALREADY_REGISTER);
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(CharacterNameException.class)
    public ResponseEntity<ErrorResponse> characterNameInvalid(final CharacterNameException ex) {
        ErrorResponse error = new ErrorResponse(INVALID_CHARACTER_NAME);
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(CharacterNameSizeException.class)
    public ResponseEntity<ErrorResponse> characterNameSizeInvalid(final CharacterNameSizeException ex) {
        ErrorResponse error = new ErrorResponse(SIZE_NAME_INVALID);
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> paramsNotFound(final MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            errorResponse.setField(((FieldError) error).getField());
            errorResponse.setMessage(error.getDefaultMessage());
        });
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
