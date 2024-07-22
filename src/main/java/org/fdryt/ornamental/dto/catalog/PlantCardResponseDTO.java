package org.fdryt.ornamental.dto.catalog;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.fdryt.ornamental.domain.plant.enums.Status;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "commonName", "scientificName", "family", "status", "photoURL" })
public record PlantCardResponseDTO(
        String id,
        String commonName,
        String scientificName,

        @JsonProperty("family")
        String familyName,

        Status status,
        String photoURL
) {}
