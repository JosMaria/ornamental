package org.fdryt.ornamental.dto.catalog;

import org.fdryt.ornamental.domain.plant.enums.Status;

public record PlantCardDTO(
        String id,
        String commonName,
        String scientificName,
        Character discoverer,
        Status status,
        String familyName
) {}
