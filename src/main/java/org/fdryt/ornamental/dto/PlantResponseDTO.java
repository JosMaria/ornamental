package org.fdryt.ornamental.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PlantResponseDTO {

    private String commonName;
    private String scientificName;
    private String family;
    private Set<String> classifications;
    private String status;
}
