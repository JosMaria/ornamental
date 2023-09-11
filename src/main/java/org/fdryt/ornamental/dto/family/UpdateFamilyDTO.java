package org.fdryt.ornamental.dto.family;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record UpdateFamilyDTO(

        @JsonProperty("name")
        @NotBlank(message = "NAME field should not be null, empty or blank")
        String name) {
}
