package com.gregorycastezana.neo.domain.usecase.impl;

import com.gregorycastezana.neo.domain.mapper.IGameMapper;
import com.gregorycastezana.neo.rest.dto.request.CharacterDTO;
import com.gregorycastezana.neo.rest.dto.response.CharactersResponse;
import com.gregorycastezana.neo.rest.dto.response.JobsResponse;
import com.gregorycastezana.neo.rest.exceptionhandler.exception.CharacterDataIntegrityException;
import com.gregorycastezana.neo.rest.exceptionhandler.exception.CharacterNameException;
import com.gregorycastezana.neo.rest.exceptionhandler.exception.CharacterNameSizeException;
import com.gregorycastezana.neo.rest.exceptionhandler.exception.JobNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class GameUseCaseImplTest {

    @Mock
    IGameMapper mapper;

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
    @DisplayName("DOMAIN LAYER ::: Get all Jobs")
    void should_Be_Return_A_List_With_All_Jobs() {
        when(mapper.toListResponse(anyList())).thenReturn(List.of(mock(JobsResponse.class)));
        assertNotNull(gameUseCase.getAllJobs());
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

    @Test
    @DisplayName("DOMAIN LAYER ::: Job not found")
    void should_Be_Throws_Job_Not_Found_Exception() {
        request = CharacterDTO.builder()
                .name("Job_Not_Found")
                .job("Troll")
                .build();

        assertThrows(JobNotFoundException.class, () -> gameUseCase.createCharacter(request));
    }

    @Test
    @DisplayName("DOMAIN LAYER ::: Get all Characters")
    void should_Be_Return_A_List_With_All_Characters() {
        when(mapper.toCharactersListResponse(anyList())).thenReturn(List.of(mock(CharactersResponse.class)));
        assertNotNull(gameUseCase.getAllCharacters());
    }
}