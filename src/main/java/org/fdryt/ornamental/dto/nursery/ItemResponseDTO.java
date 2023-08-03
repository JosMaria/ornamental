package org.fdryt.ornamental.dto.nursery;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.fdryt.ornamental.domain.plant.Status;

public class ItemResponseDTO {

    private Integer id;

    private String commonName;
    private String scientificName;
    private String scientistLastnameInitial;
    private Status status;

    @JsonProperty("family")
    private String familyName;
}
