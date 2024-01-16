package org.fdryt.ornamental.dto.plant;

import org.fdryt.ornamental.domain.plant.Classification;
import org.fdryt.ornamental.domain.plant.Status;

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
