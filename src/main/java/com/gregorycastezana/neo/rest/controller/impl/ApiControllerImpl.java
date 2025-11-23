package com.gregorycastezana.neo.rest.controller.impl;

import com.gregorycastezana.neo.rest.controller.ApiController;
import com.gregorycastezana.neo.rest.dto.request.CharacterDTO;
import com.gregorycastezana.neo.rest.dto.response.CharactersDetailsResponse;
import com.gregorycastezana.neo.rest.dto.response.CharactersResponse;
import com.gregorycastezana.neo.rest.dto.response.JobsResponse;
import com.gregorycastezana.neo.service.IGameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.gregorycastezana.neo.rest.path.Resources.V_1;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@Tag(name = "Game Features")
@RequestMapping(V_1)
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

    @Operation(summary = "List all jobs", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a list with all the jobs"),
    })
    @Override
    public ResponseEntity<List<JobsResponse>> listAllJobs() {
        return ResponseEntity.ok(gameService.getAllJobs());
    }

    @Operation(summary = "List all characters", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a list with all the characters"),
    })
    @Override
    public ResponseEntity<List<CharactersResponse>> listAllCharacters() {
        return ResponseEntity.ok(gameService.getAllCharacters());
    }

    @Operation(summary = "Get details about a character", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return details about selected character"),
    })
    @Override
    public ResponseEntity<CharactersDetailsResponse> details(@RequestParam("name") String name) {
        return ResponseEntity.ok(gameService.details(name));
    }
}
