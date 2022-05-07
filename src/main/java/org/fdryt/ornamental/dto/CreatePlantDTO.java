package org.fdryt.ornamental.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public class CreatePlantDTO {

    private final String commonName;
    private final String scientificName;
    private final Family family;
    private final Set<Classification> classifications;
}
