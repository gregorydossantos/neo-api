package com.gregorycastezana.neo.service.impl;

import com.gregorycastezana.neo.domain.usecase.IGameUseCase;
import com.gregorycastezana.neo.rest.dto.request.CharacterDTO;
import com.gregorycastezana.neo.service.IGameService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

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
}
