package org.fdryt.ornamental.dto.plant;

import org.fdryt.ornamental.domain.plant.alternative.enums.Classification;
import org.fdryt.ornamental.domain.plant.alternative.enums.Status;

import java.util.Set;

public record UpdateInformationBasicDTO(
        String commonName,
        String scientificName,
        Character scientistLastnameInitial,
        String family,
        Set<Classification> classifications,
        Status status
) {}
