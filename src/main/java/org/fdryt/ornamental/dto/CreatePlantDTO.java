package org.fdryt.ornamental.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.Set;

public record CreatePlantDTO(

        @NotBlank(message = "commonName field must not have a empty, blank or null value")
        String commonName,

        String scientificName,
        Character lastNameScientific,
        String family,
        Set<String> classifications,

        @NotBlank(message = "status field must not have a empty, blank or null value")
        String status) {

}
