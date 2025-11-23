package com.gregorycastezana.neo.rest.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetailsDTO {

    String name;
    String job;
    Long healthPoints;
    Long strength;
    Long dexterity;
    Long intelligence;
    String attackModifier;
    String speedModifier;
}
