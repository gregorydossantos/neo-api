package com.gregorycastezana.neo.rest.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gregorycastezana.neo.model.Characters;
import com.gregorycastezana.neo.model.Jobs;
import com.gregorycastezana.neo.rest.dto.request.CharacterDTO;
import com.gregorycastezana.neo.rest.dto.response.CharactersDetailsResponse;
import com.gregorycastezana.neo.rest.dto.response.CharactersResponse;
import com.gregorycastezana.neo.rest.dto.response.JobsResponse;
import com.gregorycastezana.neo.service.IGameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static com.gregorycastezana.neo.rest.path.Resources.CHARACTER_RESOURCES;
import static com.gregorycastezana.neo.rest.path.Resources.JOB_DETAILS_RESOURCES;
import static com.gregorycastezana.neo.rest.path.Resources.JOB_RESOURCES;
import static com.gregorycastezana.neo.rest.path.Resources.V_1;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
class ApiControllerImplTest {
    static final String CHARACTERS = V_1 + CHARACTER_RESOURCES;
    static final String JOBS = V_1 + JOB_RESOURCES;
    static final String JOBS_DETAILS = V_1 + JOB_DETAILS_RESOURCES;

    @MockitoBean
    IGameService gameService;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    CharacterDTO request;
    Characters characters;
    List<JobsResponse> jobsResponse;
    List<CharactersResponse> charactersResponses;

    @BeforeEach
    void setUp() {
        characters = Characters.builder()
                .id(UUID.randomUUID().toString())
                .name("Test_Warrior")
                .job(Jobs.builder()
                        .id(1L)
                        .name("Warrior")
                        .healthPoints(20L)
                        .strength(10L)
                        .dexterity(5L)
                        .intelligence(5L)
                        .attackModifier("80% of strength + 20% of dexterity")
                        .speedModifier("60% of dexterity + 20% of intelligence")
                        .build())
                .build();

        jobsResponse = List.of(mock(JobsResponse.class));
        charactersResponses = List.of(mock(CharactersResponse.class));
    }

    @Test
    @DisplayName("REST LAYER ::: Should be return a http status 201 - CREATED")
    void should_Be_Return_Success_When_Create_Character() throws Exception {
        request = CharacterDTO.builder()
                .name("Test_Warrior")
                .job("Warrior")
                .build();

        mockMvc
                .perform(post(CHARACTERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        verify(gameService, atLeastOnce()).createCharacter(request);
    }

    @Test
    @DisplayName("REST LAYER ::: Should be return a http status 404 - BAD_REQUEST - WITHOUT NAME AT PAYLOAD")
    void should_Be_Return_Error_When_Create_Character_Without_Name_Attribute() throws Exception {
        request = CharacterDTO.builder()
                .job("Warrior")
                .build();

        mockMvc
                .perform(post(CHARACTERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("REST LAYER ::: Should be return a http status 404 - BAD_REQUEST - WITH NAME NULL")
    void should_Be_Return_Error_When_Create_Character_With_Name_Null() throws Exception {
        request = CharacterDTO.builder()
                .name(null)
                .job("Warrior")
                .build();

        mockMvc
                .perform(post(CHARACTERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("REST LAYER ::: Should be return a http status 404 - BAD_REQUEST - WITH NAME BLANK")
    void should_Be_Return_Error_When_Create_Character_With_Name_Blank() throws Exception {
        request = CharacterDTO.builder()
                .name(" ")
                .job("Warrior")
                .build();

        mockMvc
                .perform(post(CHARACTERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("REST LAYER ::: Should be return a list with all jobs")
    void should_Be_Return_A_List_With_All_Jobs() throws Exception {
        when(gameService.getAllJobs()).thenReturn(jobsResponse);

        mockMvc
                .perform(get(JOBS)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(gameService, atLeastOnce()).getAllJobs();
    }

    @Test
    @DisplayName("REST LAYER ::: Should be return a list with all characters")
    void should_Be_Return_A_List_With_All_Characters() throws Exception {
        when(gameService.getAllCharacters()).thenReturn(charactersResponses);

        mockMvc
                .perform(get(CHARACTERS)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(gameService, atLeastOnce()).getAllCharacters();
    }

    @Test
    @DisplayName("REST LAYER ::: Should be return details about one character")
    void should_Be_Return_Details_About_One_Character() throws Exception {
        when(gameService.details(anyString())).thenReturn(mock(CharactersDetailsResponse.class));

        mockMvc
                .perform(get(JOBS_DETAILS).param("name", "/Oz_Mage")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(gameService, atLeastOnce()).details(anyString());
    }
}