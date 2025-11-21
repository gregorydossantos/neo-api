package com.gregorycastezana.neo.domain.usecase.impl;

import com.gregorycastezana.neo.rest.dto.request.CharacterDTO;
import com.gregorycastezana.neo.rest.exceptionhandler.exception.CharacterDataIntegrityException;
import com.gregorycastezana.neo.rest.exceptionhandler.exception.CharacterNameException;
import com.gregorycastezana.neo.rest.exceptionhandler.exception.CharacterNameSizeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class GameUseCaseImplTest {

    @InjectMocks GameUseCaseImpl gameUseCase;

    CharacterDTO request;

    @Test
    @DisplayName("DOMAIN LAYER ::: Create a character with success")
    void should_Be_Create_Character_Successfully() {
        request = CharacterDTO.builder()
                .name("Test_Warrior")
                .job("Warrior")
                .build();

        gameUseCase.createCharacter(request);
    }

    @Test
    @DisplayName("DOMAIN LAYER ::: Character already created")
    void should_Be_Throws_Character_Data_Integrity_Exception() {
        request = CharacterDTO.builder()
                .name("Great_Warrior")
                .job("Warrior")
                .build();

        assertThrows(CharacterDataIntegrityException.class, () -> gameUseCase.createCharacter(request));
    }

    @Test
    @DisplayName("DOMAIN LAYER ::: Character name invalid")
    void should_Be_Throws_Character_Name_Exception() {
        request = CharacterDTO.builder()
                .name("Mage@123")
                .job("Mage")
                .build();

        assertThrows(CharacterNameException.class, () -> gameUseCase.createCharacter(request));
    }

    @Test
    @DisplayName("DOMAIN LAYER ::: Character name size invalid, smaller than 4")
    void should_Be_Throws_Character_Name_Exception_When_Size_Smaller_Than_Four() {
        request = CharacterDTO.builder()
                .name("Mge")
                .job("Mage")
                .build();

        assertThrows(CharacterNameSizeException.class, () -> gameUseCase.createCharacter(request));
    }

    @Test
    @DisplayName("DOMAIN LAYER ::: Character name size invalid, greater than 15")
    void should_Be_Throws_Character_Name_Exception_When_Size_Greater_Than_fifteen() {
        request = CharacterDTO.builder()
                .name("WarriorAndThiefAndMage")
                .job("Mage")
                .build();

        assertThrows(CharacterNameSizeException.class, () -> gameUseCase.createCharacter(request));
    }
}