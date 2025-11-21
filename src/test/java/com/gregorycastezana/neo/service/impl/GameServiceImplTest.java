package com.gregorycastezana.neo.service.impl;

import com.gregorycastezana.neo.domain.usecase.IGameUseCase;
import com.gregorycastezana.neo.rest.dto.request.CharacterDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

    @Mock
    IGameUseCase gameUseCase;

    @InjectMocks GameServiceImpl gameService;

    @Test
    @DisplayName("SERVICE LAYER ::: Create a character with success")
    void should_Be_Create_Character() {
        var request = CharacterDTO.builder()
                .name("Test_Warrior_01")
                .job("Warrior")
                .build();

        doNothing().when(gameUseCase).createCharacter(request);
        gameService.createCharacter(request);
        verify(gameUseCase, atLeastOnce()).createCharacter(request);
    }
}