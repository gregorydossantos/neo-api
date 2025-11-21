package com.gregorycastezana.neo.rest.controller.impl;

import com.gregorycastezana.neo.model.Characters;
import com.gregorycastezana.neo.rest.dto.request.CharacterDTO;
import com.gregorycastezana.neo.service.IGameService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.UUID;

import static com.gregorycastezana.neo.rest.path.Resources.CHARACTER_RESOURCES;
import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiControllerImplTest {
    static final String BASE_PATH = "/neo/api";

    @LocalServerPort
    int port;

    @MockitoBean
    IGameService gameService;

    CharacterDTO request;
    Characters characters;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        characters = Characters.builder()
                .id(UUID.randomUUID().toString())
                .name("Test_Warrior")
                .job("Warrior")
                .build();
    }

    @Test
    @DisplayName("REST LAYER ::: Should be return a http status 201 - CREATED")
    void should_Be_Return_Success_When_Create_Character() {
        request = CharacterDTO.builder()
                .name("Test_Warrior_01")
                .job("Warrior")
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post(BASE_PATH + CHARACTER_RESOURCES)
                .then().statusCode(HttpStatus.CREATED.value());

        verify(gameService, atLeastOnce()).createCharacter(request);
    }

    @Test
    @DisplayName("REST LAYER ::: Should be return a http status 404 - BAD_REQUEST - WITHOUT NAME AT PAYLOAD")
    void should_Be_Return_Error_When_Create_Character_Without_Name_Attribute() {
        request = CharacterDTO.builder()
                .job("Warrior")
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post(BASE_PATH + CHARACTER_RESOURCES)
                .then().statusCode(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @DisplayName("REST LAYER ::: Should be return a http status 404 - BAD_REQUEST - WITH NAME NULL")
    void should_Be_Return_Error_When_Create_Character_With_Name_Null() {
        request = CharacterDTO.builder()
                .name(null)
                .job("Warrior")
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post(BASE_PATH + CHARACTER_RESOURCES)
                .then().statusCode(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @DisplayName("REST LAYER ::: Should be return a http status 404 - BAD_REQUEST - WITH NAME BLANK")
    void should_Be_Return_Error_When_Create_Character_With_Name_Blank() {
        request = CharacterDTO.builder()
                .name(" ")
                .job("Warrior")
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post(BASE_PATH + CHARACTER_RESOURCES)
                .then().statusCode(HttpStatus.BAD_REQUEST.value());

    }
}