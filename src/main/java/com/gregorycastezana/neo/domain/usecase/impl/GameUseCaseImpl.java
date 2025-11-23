package com.gregorycastezana.neo.domain.usecase.impl;

import com.gregorycastezana.neo.domain.mapper.IGameMapper;
import com.gregorycastezana.neo.domain.usecase.IGameUseCase;
import com.gregorycastezana.neo.model.Characters;
import com.gregorycastezana.neo.model.Jobs;
import com.gregorycastezana.neo.rest.dto.request.CharacterDTO;
import com.gregorycastezana.neo.rest.dto.request.CharacterListDTO;
import com.gregorycastezana.neo.rest.dto.response.CharactersResponse;
import com.gregorycastezana.neo.rest.dto.response.JobsResponse;
import com.gregorycastezana.neo.rest.exceptionhandler.exception.CharacterDataIntegrityException;
import com.gregorycastezana.neo.rest.exceptionhandler.exception.CharacterNameException;
import com.gregorycastezana.neo.rest.exceptionhandler.exception.CharacterNameSizeException;
import com.gregorycastezana.neo.rest.exceptionhandler.exception.JobNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.gregorycastezana.neo.domain.constants.StatusEnum.ALIVE;
import static com.gregorycastezana.neo.domain.constants.StatusEnum.DEAD;
import static com.gregorycastezana.neo.domain.message.CommonsMessages.CHARACTER_ALREADY_REGISTER;
import static com.gregorycastezana.neo.domain.message.CommonsMessages.INVALID_CHARACTER_NAME;
import static com.gregorycastezana.neo.domain.message.CommonsMessages.JOB_NOT_FOUND;
import static com.gregorycastezana.neo.domain.message.CommonsMessages.SIZE_NAME_INVALID;
import static com.gregorycastezana.neo.domain.useful.StringPattern.isValidName;
import static com.gregorycastezana.neo.domain.useful.StringPattern.isInvalidSizeName;
import static lombok.AccessLevel.PRIVATE;

@Log4j2
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class GameUseCaseImpl implements IGameUseCase {
    private static final String CREATING_NEW_CHARACTER = "Creating a new character based on request: {}";
    private static final String VALIDATE_CHARACTER = "Validate if character already exists by name {}";
    private static final String CHARACTER_CREATED = "Character successfully created!";
    private static final String DETAILS = "Click here";

    List<Jobs> jobs = initializeJobsData();
    List<Characters> characters = initializeCharactersData(jobs);

    IGameMapper gameMapper;

    @Override
    public void createCharacter(CharacterDTO request) {
        log.info(CREATING_NEW_CHARACTER, request);

        log.info(VALIDATE_CHARACTER, request.getName());
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
                .job(getJobByName(request.getJob()))
                .build();

        log.info(CHARACTER_CREATED);
    }

    @Override
    public List<JobsResponse> getAllJobs() {
        return gameMapper.toListResponse(jobs);
    }

    @Override
    public List<CharactersResponse> getAllCharacters() {
        var charactersDto = buildCharactersList(characters);
        return gameMapper.toCharactersListResponse(charactersDto);
    }

    private boolean characterExists(String name) {
        return characters.stream().anyMatch(c ->
                c.getName().equalsIgnoreCase(name));
    }

    private Jobs getJobByName(String name) {
        return jobs.stream().filter(j -> j.getName().equalsIgnoreCase(name)).findFirst()
                .orElseThrow(() -> new JobNotFoundException(JOB_NOT_FOUND));
    }

    private List<CharacterListDTO> buildCharactersList(List<Characters> characters) {
        List<CharacterListDTO> response = new ArrayList<>();
        for (Characters c : characters) {
            response.add(
                    CharacterListDTO.builder()
                            .characterName(c.getName())
                            .jobName(c.getJob().getName())
                            .status(setStatus(c.getJob().getHealthPoints()))
                            .details(DETAILS)
                            .build()
            );
        }
        return response;
    }

    private String setStatus(Long hp) {
        if (hp > 0) {
            return ALIVE.getStatus();
        }
        return DEAD.getStatus();
    }

    private List<Characters> initializeCharactersData(List<Jobs> jobs) {
        List<Characters> mockData = new ArrayList<>();
        Characters c1 = Characters.builder()
                .id(UUID.randomUUID().toString())
                .name("Great_Warrior")
                .job(jobs.get(0))
                .build();
        Characters c2 = Characters.builder()
                .id(UUID.randomUUID().toString())
                .name("Thief_001")
                .job(jobs.get(1))
                .build();
        Characters c3 = Characters.builder()
                .id(UUID.randomUUID().toString())
                .name("Oz_Mage")
                .job(jobs.get(2))
                .build();

        mockData.add(c1);
        mockData.add(c2);
        mockData.add(c3);
        return mockData;
    }

    private List<Jobs> initializeJobsData() {
        List<Jobs> mockJob = new ArrayList<>();
        Jobs j1 = Jobs.builder()
                .id(1L)
                .name("Warrior")
                .healthPoints(20L)
                .strength(10L)
                .dexterity(5L)
                .intelligence(5L)
                .attackModifier("80% of strength + 20% of dexterity")
                .speedModifier("60% of dexterity + 20% of intelligence")
                .build();

        Jobs j2 = Jobs.builder()
                .id(1L)
                .name("Thief")
                .healthPoints(15L)
                .strength(4L)
                .dexterity(10L)
                .intelligence(4L)
                .attackModifier("25% of strength + 100% of dexterity + 25% of intelligence")
                .speedModifier("80% of dexterity")
                .build();

        Jobs j3 = Jobs.builder()
                .id(1L)
                .name("Mage")
                .healthPoints(12L)
                .strength(5L)
                .dexterity(6L)
                .intelligence(10L)
                .attackModifier("20% of strength + 20% of dexterity + 120% of intelligence")
                .speedModifier("40% of dexterity + 10% of strength")
                .build();

        mockJob.add(j1);
        mockJob.add(j2);
        mockJob.add(j3);
        return mockJob;
    }
}
