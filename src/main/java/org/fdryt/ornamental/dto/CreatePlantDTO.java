package org.fdryt.ornamental.dto;

import lombok.Builder;
import lombok.Getter;
import org.fdryt.ornamental.domain.ClassificationByUtility;
import org.fdryt.ornamental.domain.Family;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Set;

@Getter
@Builder
public class CreatePlantDTO {

    @NotNull(message = "commonName field must not have a null value")
    @Pattern(regexp = "^[A-Z][a-z_ ]+",
            message = "commonName field must have a format 'first capital letter and the rest lowercase'")
    private String commonName;

    @Pattern(regexp = "^[A-Z][a-z_ ]+",
            message = "scientificName field must have a format 'first capital letter and the rest lowercase'")
    private String scientificName;

    @NotNull(message = "family field must not have a null value")
    private Family family;

    @NotNull(message = "classifications field must not have a null value")
    private Set<ClassificationByUtility> classifications;
}
