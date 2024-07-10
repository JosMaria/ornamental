package org.fdryt.ornamental.dto.alternative;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.fdryt.ornamental.domain.plant.alternative.enums.Classification;
import org.fdryt.ornamental.domain.plant.alternative.enums.Status;
import org.fdryt.ornamental.dto.plant.TechnicalSheetDTO;

import java.util.List;
import java.util.Set;

public record PlantRequestDTO(

        @Pattern(regexp = "^[a-z]+( [a-z]+)*$", message = "the value for key 'commonName' must only have lowercase letters and a space for each letter")
        @NotBlank(message = "the value for key 'commonName' must not be null, blank or empty")
        String commonName,

        @Pattern(regexp = "^[a-z]+( [a-z]+)*$", message = "the value for key 'scientificName' must only have lowercase letters and a space for each letter")
        String scientificName,

        Character scientistLastnameInitial,

        @JsonProperty("family")
        String familyName,

        @NotNull(message = "the value for key 'classifications' must not be null")
        Set<Classification> classifications,

        List<TechnicalSheetDTO> technicalSheet,

        List<String> details,

        String description,

        @JsonEnumDefaultValue()
        @NotBlank(message = "the value for key 'commonName' must not be null, blank or empty")
        Status status,

        @DecimalMin(value = "0.0", inclusive = false, message = "the value for key 'price' must be greater than 0")
        Double price
) {}
