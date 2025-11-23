package com.gregorycastezana.neo.domain.mapper;

import com.gregorycastezana.neo.model.Jobs;
import com.gregorycastezana.neo.rest.dto.request.CharacterListDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class IGameMapperTest {

    @Autowired
    IGameMapper mapper;

    @Test
    void should_Be_Return_A_Jobs_List_Response() {
        var mockJobs = List.of(mock(Jobs.class));
        assertNotNull(mapper.toListResponse(mockJobs));
    }

    @Test
    void should_Be_Return_A_Characters_List_Response() {
        var mockJobs = List.of(mock(CharacterListDTO.class));
        assertNotNull(mapper.toCharactersListResponse(mockJobs));
    }
}