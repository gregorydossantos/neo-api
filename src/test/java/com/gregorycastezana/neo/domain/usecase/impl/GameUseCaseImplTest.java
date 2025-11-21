package com.gregorycastezana.neo.domain.usecase.impl;

import com.gregorycastezana.neo.rest.dto.request.CharacterDTO;
import com.gregorycastezana.neo.rest.exceptionhandler.exception.CharacterDataIntegrityException;
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
                .name("Test_Warrior_01")
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


}