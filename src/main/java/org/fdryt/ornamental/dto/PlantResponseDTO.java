package org.fdryt.ornamental.dto;

import lombok.Getter;
import lombok.Setter;
import org.fdryt.ornamental.domain.ClassificationByUtility;
import org.fdryt.ornamental.domain.Family;

import java.util.Set;

@Getter
@Setter
public class PlantResponseDTO {

    private String commonName;
    private String scientificName;
    private Family family;
    private Set<ClassificationByUtility> classifications;
}
