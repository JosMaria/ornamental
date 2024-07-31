package org.fdryt.ornamental.dto.catalog;

public record ItemDTO(
        String commonName,
        String scientificName,
        Character discoverer,
        String familyName
) {}
