package org.fdryt.ornamental.dto.alternative;

import org.fdryt.ornamental.domain.plant.alternative.enums.Classification;
import org.fdryt.ornamental.domain.plant.alternative.enums.Status;
import org.fdryt.ornamental.dto.plant.TechnicalSheetDTO;

import java.util.Collection;
import java.util.Set;

public record PlantResponseDTO(
    Integer id,
    String family,
    String commonName,
    String scientificName,
    Status status,
    String description,
    Collection<String> details,
    Set<Classification> classifications,
    Collection<TechnicalSheetDTO> technicalSheet
) {}
