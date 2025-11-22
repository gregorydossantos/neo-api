package com.gregorycastezana.neo.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;
import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
public class JobsResponse {

    @JsonProperty(access = WRITE_ONLY)
    Long id;
    @JsonProperty("Job")
    String name;
    @JsonProperty("Life Points")
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
