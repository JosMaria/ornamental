package org.fdryt.ornamental.dto.plant;

import org.fdryt.ornamental.domain.plant.Classification;
import org.fdryt.ornamental.domain.plant.Status;

import java.util.Set;

public record UpdateInformationBasicDTO(
        String commonName,
        String scientificName,
        Character scientistLastnameInitial,
        String family,
        Set<Classification> classifications,
        Status status
) {}
