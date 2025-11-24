package com.gregorycastezana.neo.service.impl;

import com.gregorycastezana.neo.domain.usecase.IGameUseCase;
import com.gregorycastezana.neo.rest.dto.request.CharacterDTO;
import com.gregorycastezana.neo.rest.dto.response.CharactersDetailsResponse;
import com.gregorycastezana.neo.rest.dto.response.CharactersResponse;
import com.gregorycastezana.neo.rest.dto.response.JobsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
                .name("Test_Warrior")
                .job("Warrior")
                .build();

        doNothing().when(gameUseCase).createCharacter(request);
        gameService.createCharacter(request);
        verify(gameUseCase, atLeastOnce()).createCharacter(request);
    }

    @Test
    @DisplayName("SERVICE LAYER ::: Return a list of all jobs")
    void should_Be_Return_List_With_All_Jobs() {
        when(gameUseCase.getAllJobs()).thenReturn(List.of(mock(JobsResponse.class)));
        assertNotNull(gameService.getAllJobs());
    }

    @Test
    @DisplayName("SERVICE LAYER ::: Return a list of all characters")
    void should_Be_Return_List_With_All_characters() {
        when(gameUseCase.getAllCharacters()).thenReturn(List.of(mock(CharactersResponse.class)));
        assertNotNull(gameService.getAllCharacters());
    }

    @Test
    @DisplayName("SERVICE LAYER ::: Return details about one character")
    void should_Be_Return_Details_About_One_character() {
        when(gameUseCase.details(anyString())).thenReturn(mock(CharactersDetailsResponse.class));
        assertNotNull(gameService.details(anyString()));
    }

    @Test
    @DisplayName("SERVICE LAYER ::: Starting a battle")
    void should_Be_Start_Battle_Successfully() {
        gameService.battle(anyString(), anyString());
    }
}