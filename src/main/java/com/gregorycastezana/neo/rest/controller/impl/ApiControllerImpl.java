package com.gregorycastezana.neo.rest.controller.impl;

import com.gregorycastezana.neo.rest.controller.ApiController;
import com.gregorycastezana.neo.rest.dto.request.CharacterDTO;
import com.gregorycastezana.neo.service.IGameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@Tag(name = "Game Features")
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ApiControllerImpl implements ApiController {

    IGameService gameService;

    @Operation(summary = "Create a character", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Return HTTP status CREATED"),
            @ApiResponse(responseCode = "406", description = "Character already register!")
    })
    @Override
    public ResponseEntity<Void> createCharacter(CharacterDTO request) {
        gameService.createCharacter(request);
        return ResponseEntity.status(CREATED).build();
    }
}
