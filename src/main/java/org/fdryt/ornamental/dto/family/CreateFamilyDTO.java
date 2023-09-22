package org.fdryt.ornamental.dto.family;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record CreateFamilyDTO(
        @JsonProperty("family_name")
        @NotBlank(message = "FAMILY_NAME field should not be null, empty or blank")
        String name) {
}
