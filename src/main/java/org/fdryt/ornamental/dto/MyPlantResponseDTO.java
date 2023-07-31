package org.fdryt.ornamental.dto;

import lombok.Getter;
import lombok.Setter;
import org.fdryt.ornamental.domain.plant.Classification;
import org.fdryt.ornamental.domain.plant.Status;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class MyPlantResponseDTO {

    private Integer id;
    private String commonName;
    private String scientificName;
    private String family;
    private Set<Classification> classifications;
    private Status status;
    private Collection<String> details;
    private Collection<String> notes;
}
