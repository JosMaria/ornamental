package org.fdryt.ornamental.dto.plant;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.fdryt.ornamental.domain.plant.alternative.enums.Classification;
import org.fdryt.ornamental.domain.plant.alternative.enums.Status;
import org.fdryt.ornamental.utils.validations.ValidEnum;

import java.util.Set;

public record PlantRequestDTO(

        @Pattern(regexp = "^[a-z]+( [a-z]+)*$", message = "the value for key 'commonName' must only have lowercase letters and a space for each letter")
        @NotBlank(message = "the value for key 'commonName' must not be null, blank or empty")
        String commonName,

        @Pattern(regexp = "^[a-z]+( [a-z]+)*$", message = "the value for key 'scientificName' must only have lowercase letters and a space for each letter")
        String scientificName,

        Character discoverer,

        @ValidEnum(enumClass = Status.class)
        Status status,

        @JsonProperty("family")
        String familyName,

        @ValidEnum(enumClass = Classification.class)
        Set<Classification> classifications/*,

        List<TechnicalSheetDTO> technicalSheet,

        List<String> details,

        String description,

        @DecimalMin(value = "0.0", inclusive = false, message = "the value for key 'price' must be greater than 0")
        Double price*/
) {}
