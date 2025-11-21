package com.gregorycastezana.neo.domain.usecase.impl;

import com.gregorycastezana.neo.domain.usecase.IGameUseCase;
import com.gregorycastezana.neo.model.Characters;
import com.gregorycastezana.neo.rest.dto.request.CharacterDTO;
import com.gregorycastezana.neo.rest.exceptionhandler.exception.CharacterDataIntegrityException;
import com.gregorycastezana.neo.rest.exceptionhandler.exception.CharacterNameException;
import com.gregorycastezana.neo.rest.exceptionhandler.exception.CharacterNameSizeException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.gregorycastezana.neo.domain.message.CommonsMessages.CHARACTER_ALREADY_REGISTER;
import static com.gregorycastezana.neo.domain.message.CommonsMessages.INVALID_CHARACTER_NAME;
import static com.gregorycastezana.neo.domain.message.CommonsMessages.SIZE_NAME_INVALID;
import static com.gregorycastezana.neo.domain.useful.StringPattern.isValidName;
import static com.gregorycastezana.neo.domain.useful.StringPattern.isInvalidSizeName;
import static lombok.AccessLevel.PRIVATE;

@Log4j2
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class GameUseCaseImpl implements IGameUseCase {

    List<Characters> characters = initializeCharactersData();

    @Override
    public void createCharacter(CharacterDTO request) {
        log.info("Creating a new character based on request: {}", request);

        log.info("Validate if character already exists by name {}", request.getName());
        if (characterExists(request.getName())) {
            throw new CharacterDataIntegrityException(CHARACTER_ALREADY_REGISTER);
        }

        if (isInvalidSizeName(request.getName())) {
            throw new CharacterNameSizeException(SIZE_NAME_INVALID);
        }
        if(!isValidName(request.getName())) {
            throw new CharacterNameException(INVALID_CHARACTER_NAME);
        }

        Characters.builder()
                .name(request.getName())
                .job(request.getJob())
                .build();

        log.info("Character successfully created!");
    }

    private boolean characterExists(String name) {
        return characters.stream().anyMatch(c ->
                c.getName().equalsIgnoreCase(name));
    }

    private List<Characters> initializeCharactersData() {
        List<Characters> mockData = new ArrayList<>();
        Characters c1 = Characters.builder()
                .id(UUID.randomUUID().toString())
                .name("Great_Warrior")
                .job("Warrior")
                .build();
        Characters c2 = Characters.builder()
                .id(UUID.randomUUID().toString())
                .name("Thief_001")
                .job("Thief")
                .build();
        Characters c3 = Characters.builder()
                .id(UUID.randomUUID().toString())
                .name("Oz_Mage")
                .job("Mage")
                .build();

        mockData.add(c1);
        mockData.add(c2);
        mockData.add(c3);
        return mockData;
    }
}
