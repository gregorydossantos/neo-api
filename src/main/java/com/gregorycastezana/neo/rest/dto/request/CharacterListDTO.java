package com.gregorycastezana.neo.rest.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CharacterListDTO {
    private String characterName;
    private String jobName;
    private String status;
    private String details;
}
