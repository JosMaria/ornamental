package org.fdryt.ornamental.dto;

import org.fdryt.ornamental.domain.plant.Status;

import java.util.List;

public record MyCreatePlantDTO(
    String commonName,
    String scientificName,
    Character scientistLastnameInitial,
    Status status,
    List<String> notes
) {}
