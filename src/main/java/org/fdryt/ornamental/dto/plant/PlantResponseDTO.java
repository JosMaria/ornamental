package org.fdryt.ornamental.dto.plant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.fdryt.ornamental.domain.plant.enums.Classification;
import org.fdryt.ornamental.domain.plant.enums.Status;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PlantResponseDTO(
    Long id,
    String commonName,
    String scientificName,
    Character discoverer,
    Status status,

    @JsonProperty("family")
    String familyName,

    Set<Classification> classifications
) {}
