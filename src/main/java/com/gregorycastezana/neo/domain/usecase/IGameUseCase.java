package com.gregorycastezana.neo.domain.usecase;

import com.gregorycastezana.neo.model.Jobs;
import com.gregorycastezana.neo.rest.dto.request.CharacterDTO;
import com.gregorycastezana.neo.rest.dto.response.JobsResponse;

import java.util.List;

public interface IGameUseCase {

    void createCharacter(CharacterDTO request);

    List<JobsResponse> getAllJobs();
}
