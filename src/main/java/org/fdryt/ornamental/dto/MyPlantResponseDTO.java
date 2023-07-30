package org.fdryt.ornamental.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class MyPlantResponseDTO {

    private Integer id;
    private String commonName;
    private String scientificName;
    private Collection<String> notes;
}
