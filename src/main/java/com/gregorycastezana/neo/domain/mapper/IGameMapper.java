package com.gregorycastezana.neo.domain.mapper;

import com.gregorycastezana.neo.model.Jobs;
import com.gregorycastezana.neo.rest.dto.request.CharacterListDTO;
import com.gregorycastezana.neo.rest.dto.request.DetailsDTO;
import com.gregorycastezana.neo.rest.dto.response.CharactersDetailsResponse;
import com.gregorycastezana.neo.rest.dto.response.CharactersResponse;
import com.gregorycastezana.neo.rest.dto.response.JobsResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IGameMapper {

    List<JobsResponse> toListResponse(List<Jobs> jobs);

    List<CharactersResponse> toCharactersListResponse(List<CharacterListDTO> dto);

    CharactersDetailsResponse toDetailsResponse(DetailsDTO dto);
}
