package org.fdryt.ornamental.dto.plant;

import lombok.Getter;
import lombok.Setter;
import org.fdryt.ornamental.domain.plant.Classification;
import org.fdryt.ornamental.domain.plant.Status;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
public class PlantResponseDTO {

    private Integer id;
    private String commonName;
    private String scientificName;
    private String family;
    private Set<Classification> classifications;
    private Status status;
    private String description;
    private Collection<String> details;
    private Collection<String> notes;
    private Collection<TechnicalSheetDTO> technicalSheet;
}
