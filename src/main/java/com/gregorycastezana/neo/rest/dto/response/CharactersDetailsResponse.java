package com.gregorycastezana.neo.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
public class CharactersDetailsResponse {

    @JsonProperty("Name")
    String name;
    @JsonProperty("Job")
    String job;
    @JsonProperty("Current Life Points")
    Long healthPoints;
    @JsonProperty("Strength")
    Long strength;
    @JsonProperty("Dexterity")
    Long dexterity;
    @JsonProperty("Intelligence")
    Long intelligence;
    @JsonProperty("Attack")
    String attackModifier;
    @JsonProperty("Speed")
    String speedModifier;
}
