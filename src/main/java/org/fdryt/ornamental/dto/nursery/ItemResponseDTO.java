package org.fdryt.ornamental.dto.nursery;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ItemResponseDTO(
        Integer id,
        String commonName,
        String scientificName,
        Character scientistLastnameInitial,

        @JsonProperty("family")
        String familyName
) {}
