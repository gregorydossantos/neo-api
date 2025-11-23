package com.gregorycastezana.neo.domain.mapper;

import com.gregorycastezana.neo.model.Jobs;
import com.gregorycastezana.neo.rest.dto.request.CharacterListDTO;
import com.gregorycastezana.neo.rest.dto.response.CharactersResponse;
import com.gregorycastezana.neo.rest.dto.response.JobsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IGameMapper {

    List<JobsResponse> toListResponse(List<Jobs> jobs);

    @Mapping(source = "name", target = "characterName")
    @Mapping(source = "job", target = "jobName")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "details", target = "details")
    List<CharactersResponse> toCharactersListResponse(List<CharacterListDTO> dto);
}
