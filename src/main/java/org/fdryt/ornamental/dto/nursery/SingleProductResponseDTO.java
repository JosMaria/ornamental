package org.fdryt.ornamental.dto.nursery;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.fdryt.ornamental.domain.plant.Classification;
import org.fdryt.ornamental.domain.plant.Status;
import org.fdryt.ornamental.dto.plant.TechnicalSheetDTO;

import java.util.List;
import java.util.Set;

public record SingleProductResponseDTO(
    Integer id,
    String commonName,
    String scientificName,
    Character scientistLastnameInitial,
    String family,
    Status status,
    Set<Classification> classifications,
    String description,
    @JsonProperty("photos_URL")
    Set<String> photosUrl,
    List<String> details,
    List<TechnicalSheetDTO> technicalSheet,
    Double price
) {}
