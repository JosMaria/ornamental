package org.fdryt.ornamental.dto.nursery;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.fdryt.ornamental.domain.plant.Status;

public record ProductResponseDTO(
    Integer id,
    String commonName,
    String scientificName,
    Character scientistLastnameInitial,
    Status status,

    @JsonProperty("family")
    String familyName,

    @JsonProperty("photo_URL")
    String firstPhotoURL
) {}
