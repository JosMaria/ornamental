package org.fdryt.ornamental.dto;

import lombok.Setter;
import org.fdryt.ornamental.domain.Classification;
import org.fdryt.ornamental.domain.Family;

import java.util.Set;


@Setter
public class PlantResponseDTO {

    private String commonName;
    private String scientificName;
    private Family family;
    private Set<Classification> classifications;
}
