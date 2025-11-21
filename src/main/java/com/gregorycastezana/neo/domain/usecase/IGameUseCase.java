package com.gregorycastezana.neo.domain.usecase;

import com.gregorycastezana.neo.rest.dto.request.CharacterDTO;

public interface IGameUseCase {

    void createCharacter(CharacterDTO request);
}
