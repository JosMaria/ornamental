package org.fdryt.ornamental.dto.identification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.fdryt.ornamental.domain.Status;

@Getter
@SuperBuilder
public class ItemToListResponseDTO {

    private Integer id;
    private String commonName;
    private String scientificName;

    @JsonProperty("firstLetterLastname")
    private Character plusScientificName;

    @JsonProperty("family")
    private String familyName;
    private Status status;
}

