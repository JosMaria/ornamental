package org.fdryt.ornamental.dto.plant;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.fdryt.ornamental.domain.plant.alternative.enums.Classification;
import org.fdryt.ornamental.domain.plant.alternative.enums.Status;

import java.util.List;
import java.util.Set;

public record CreatePlantDTO(
    @NotBlank(message = "COMMON_NAME field should not be null, empty or blank")
    String commonName,

    String scientificName,
    Character scientistLastnameInitial,

    @JsonProperty("family")
    String familyName,

    Set<Classification> classifications,
    Status status,
    String description,
    List<String> details,
    List<TechnicalSheetDTO> technicalSheet,
    Double price
) {}
