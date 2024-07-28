package org.fdryt.ornamental.dto.catalog;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.fdryt.ornamental.domain.plant.enums.Status;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "commonName", "scientificName", "status", "imageId" })
public record PlantCardResponseDTO(
        Long id,
        String commonName,
        String scientificName,
        Status status,
        String imageId
) {}
