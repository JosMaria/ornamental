package org.fdryt.ornamental.dto.identification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.fdryt.ornamental.domain.Status;

@Getter
@Builder
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
