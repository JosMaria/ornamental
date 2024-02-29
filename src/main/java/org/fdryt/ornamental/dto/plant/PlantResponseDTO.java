package org.fdryt.ornamental.dto.plant;

import org.fdryt.ornamental.domain.plant.alternative.enums.Classification;
import org.fdryt.ornamental.domain.plant.alternative.enums.Status;

import java.util.Collection;
import java.util.Set;

public record PlantResponseDTO(
    Integer id,
    String commonName,
    String scientificName,
    String family,
    Set<Classification> classifications,
    Status status,
    String description,
    Collection<String> details,
    Collection<TechnicalSheetDTO> technicalSheet
) {}
