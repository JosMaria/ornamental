package org.fdryt.ornamental.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.domain.Classification;
import org.fdryt.ornamental.domain.Family;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public class CreatePlantDTO {

    @NotNull
    @Pattern(regexp = "^[A-Z][a-z_ ]+")
    private final String commonName;

    @Pattern(regexp = "^[A-Z][a-z_ ]+")
    private final String scientificName;

    private final Family family;
    private final Set<Classification> classifications;
}
