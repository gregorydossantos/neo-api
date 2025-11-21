package com.gregorycastezana.neo.rest.exceptionhandler;

import com.gregorycastezana.neo.rest.exceptionhandler.exception.CharacterDataIntegrityException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static com.gregorycastezana.neo.domain.message.CommonsMessages.CHARACTER_ALREADY_REGISTER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class GameExceptionHandlerTest {

    @InjectMocks GameExceptionHandler gameExceptionHandler;

    @Test
    @DisplayName("REST LAYER ::: Should be throws Character Data Integrity Exception")
    void should_Be_Throws_Character_Data_Integrity_Exception() {
        ResponseEntity<ErrorResponse> response = gameExceptionHandler.characterDataIntegrity(
                new CharacterDataIntegrityException(CHARACTER_ALREADY_REGISTER));
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
    }

    @Test
    @DisplayName("REST LAYER ::: Should be throws Method Argument Not Valid Exception")
    void should_Be_Throws_Method_Argument_Not_Valid_Exception() {
        var parameter = mock(MethodParameter.class);
        var bindingResult = mock(BindingResult.class);
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(parameter, bindingResult);
        ResponseEntity<ErrorResponse> response = gameExceptionHandler.paramsNotFound(exception);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}