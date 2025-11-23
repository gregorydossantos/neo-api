package com.gregorycastezana.neo.rest.exceptionhandler;

import com.gregorycastezana.neo.rest.exceptionhandler.exception.CharacterDataIntegrityException;
import com.gregorycastezana.neo.rest.exceptionhandler.exception.CharacterNameException;
import com.gregorycastezana.neo.rest.exceptionhandler.exception.CharacterNameSizeException;
import com.gregorycastezana.neo.rest.exceptionhandler.exception.DetailsNotFoundException;
import com.gregorycastezana.neo.rest.exceptionhandler.exception.JobNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static com.gregorycastezana.neo.domain.message.CommonsMessages.CHARACTER_ALREADY_REGISTER;
import static com.gregorycastezana.neo.domain.message.CommonsMessages.INVALID_CHARACTER_NAME;
import static com.gregorycastezana.neo.domain.message.CommonsMessages.JOB_NOT_FOUND;
import static com.gregorycastezana.neo.domain.message.CommonsMessages.SIZE_NAME_INVALID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    @DisplayName("REST LAYER ::: Should be throws Character Name Exception")
    void should_Be_Throws_Character_Name_Exception() {
        ResponseEntity<ErrorResponse> response = gameExceptionHandler.characterNameInvalid(
                new CharacterNameException(INVALID_CHARACTER_NAME));
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
    }

    @Test
    @DisplayName("REST LAYER ::: Should be throws Character Name Size Exception")
    void should_Be_Throws_Character_Name_Size_Exception() {
        ResponseEntity<ErrorResponse> response = gameExceptionHandler.characterNameSizeInvalid(
                new CharacterNameSizeException(SIZE_NAME_INVALID));
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

    @Test
    @DisplayName("REST LAYER ::: Should be throws Job Not Found Exception")
    void should_Be_Throws_Job_Not_Found_Exception() {
        ResponseEntity<ErrorResponse> response = gameExceptionHandler.jobNotFound(
                new JobNotFoundException(JOB_NOT_FOUND));
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("REST LAYER ::: Should be throws Details Not Found Exception")
    void should_Be_Throws_Details_Not_Found_Exception() {
        ResponseEntity<ErrorResponse> response = gameExceptionHandler.detailsNotFound(
                new DetailsNotFoundException(JOB_NOT_FOUND));
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}