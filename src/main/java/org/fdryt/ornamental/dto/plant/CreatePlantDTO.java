package org.fdryt.ornamental.dto.plant;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record CreatePlantDTO(
        @NotBlank(message = "COMMON_NAME field should not be null, empty or blank")
        String commonName,

        String scientificName,
        Character scientistSurnameInitial,
        String family,
        Set<String> classifications,

        @NotBlank(message = "STATUS field should not be null, empty or blank")
        String status
) {}
