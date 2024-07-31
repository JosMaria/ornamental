package org.fdryt.ornamental.dto.catalog;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "commonName", "scientificName", "family" })
public record ItemResponseDTO(
    String commonName,
    String scientificName,

    @JsonProperty("family")
    String familyName
) {}
