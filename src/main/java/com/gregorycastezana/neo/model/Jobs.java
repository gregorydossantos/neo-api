package com.gregorycastezana.neo.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
public class Jobs {
    Long id;
    String name;
    Long healthPoints;
    Long strength;
    Long dexterity;
    Long intelligence;
    String attackModifier;
    String speedModifier;
}
