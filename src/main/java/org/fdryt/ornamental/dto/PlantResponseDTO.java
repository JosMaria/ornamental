package org.fdryt.ornamental.dto;

import lombok.Setter;
import org.fdryt.ornamental.domain.TypeClassification;
import org.fdryt.ornamental.domain.TypeFamily;

import java.util.Set;

@Setter
public class PlantResponseDTO {

    private String commonName;
    private String scientificName;
    private TypeFamily family;
    private Set<TypeClassification> classifications;
}
