package org.fdryt.ornamental.dto.family;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record FamilyRequestDTO(

        @Pattern(regexp = "^[a-z]+$", message = "the value for the key 'name' must be a word containing lowercase letters")
        @NotNull(message = "the value for key 'name' must not be null")
        String name
) {}
