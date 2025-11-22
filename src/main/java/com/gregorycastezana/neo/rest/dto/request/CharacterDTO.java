package com.gregorycastezana.neo.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import static com.gregorycastezana.neo.domain.message.CommonsMessages.FIELD_MANDATORY;
import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
public class CharacterDTO {
    @NotBlank(message = FIELD_MANDATORY)
    private String name;

    @NotBlank(message = FIELD_MANDATORY)
    private String job;
}
