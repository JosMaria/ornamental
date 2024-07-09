package org.fdryt.ornamental.dto.repertory;

public record ItemDTO(
        String commonName,
        String scientificName,
        Character discoverer,
        String familyName
) {}
