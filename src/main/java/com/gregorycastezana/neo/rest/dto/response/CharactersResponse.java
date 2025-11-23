package com.gregorycastezana.neo.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
public class CharactersResponse {

    @JsonProperty("Character Name")
    String characterName;
    @JsonProperty("Job")
    String jobName;
    @JsonProperty("Status")
    String status;
    @JsonProperty("Details")
    String details;
}
