package com.gregorycastezana.neo.rest.controller;

import com.gregorycastezana.neo.rest.dto.request.CharacterDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.gregorycastezana.neo.rest.path.Resources.CHARACTER_RESOURCES;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface ApiController {

    @PostMapping(value = CHARACTER_RESOURCES, consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> createCharacter(@Valid @RequestBody CharacterDTO request);
}
