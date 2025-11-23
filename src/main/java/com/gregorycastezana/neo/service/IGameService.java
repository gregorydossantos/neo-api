package com.gregorycastezana.neo.service;

import com.gregorycastezana.neo.model.Jobs;
import com.gregorycastezana.neo.rest.dto.request.CharacterDTO;
import com.gregorycastezana.neo.rest.dto.response.CharactersResponse;
import com.gregorycastezana.neo.rest.dto.response.JobsResponse;

import java.util.List;

public interface IGameService {

    void createCharacter(CharacterDTO request);

    List<JobsResponse> getAllJobs();

    List<CharactersResponse> getAllCharacters();
}
