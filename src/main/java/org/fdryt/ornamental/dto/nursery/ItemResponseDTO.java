package org.fdryt.ornamental.dto.nursery;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.fdryt.ornamental.domain.plant.Status;

public record ItemResponseDTO(
        Integer id,
        String commonName,
        String scientificName,
        String scientistLastnameInitial,
        Status status,
        @JsonProperty("family")
        String familyName
) {}
