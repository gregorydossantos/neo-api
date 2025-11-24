package com.gregorycastezana.neo.rest.controller;

import com.gregorycastezana.neo.rest.dto.request.CharacterDTO;
import com.gregorycastezana.neo.rest.dto.response.CharactersDetailsResponse;
import com.gregorycastezana.neo.rest.dto.response.CharactersResponse;
import com.gregorycastezana.neo.rest.dto.response.JobsResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.gregorycastezana.neo.rest.path.Resources.BATTLE_RESOURCES;
import static com.gregorycastezana.neo.rest.path.Resources.CHARACTER_RESOURCES;
import static com.gregorycastezana.neo.rest.path.Resources.JOB_DETAILS_RESOURCES;
import static com.gregorycastezana.neo.rest.path.Resources.JOB_RESOURCES;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface ApiController {

    @PostMapping(value = CHARACTER_RESOURCES, consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> createCharacter(@Valid @RequestBody CharacterDTO request);

    @GetMapping(value = JOB_RESOURCES, consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<List<JobsResponse>> listAllJobs();

    @GetMapping(value = CHARACTER_RESOURCES, consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<List<CharactersResponse>> listAllCharacters();

    @GetMapping(value = JOB_DETAILS_RESOURCES, consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<CharactersDetailsResponse> details(@RequestParam("name") String name);

    @PostMapping(value = BATTLE_RESOURCES, consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> battle(@RequestParam("character_one") String c1,
                                @RequestParam("character_two") String c2);
}
