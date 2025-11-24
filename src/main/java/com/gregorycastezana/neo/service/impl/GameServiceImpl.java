package com.gregorycastezana.neo.service.impl;

import com.gregorycastezana.neo.domain.usecase.IGameUseCase;
import com.gregorycastezana.neo.rest.dto.request.CharacterDTO;
import com.gregorycastezana.neo.rest.dto.response.CharactersDetailsResponse;
import com.gregorycastezana.neo.rest.dto.response.CharactersResponse;
import com.gregorycastezana.neo.rest.dto.response.JobsResponse;
import com.gregorycastezana.neo.service.IGameService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class GameServiceImpl implements IGameService {

    IGameUseCase gameUseCase;

    @Override
    public void createCharacter(CharacterDTO request) {
        gameUseCase.createCharacter(request);
    }

    @Override
    public List<JobsResponse> getAllJobs() {
        return gameUseCase.getAllJobs();
    }

    @Override
    public List<CharactersResponse> getAllCharacters() {
        return gameUseCase.getAllCharacters();
    }

    @Override
    public CharactersDetailsResponse details(String name) {
        return gameUseCase.details(name);
    }

    @Override
    public void battle(String c1, String c2) {
        gameUseCase.battle(c1, c2);
    }
}
