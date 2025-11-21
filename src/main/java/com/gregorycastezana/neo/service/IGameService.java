package com.gregorycastezana.neo.service;

import com.gregorycastezana.neo.rest.dto.request.CharacterDTO;

public interface IGameService {

    void createCharacter(CharacterDTO request);
}
