package org.fdryt.ornamental.dto.nursery;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fdryt.ornamental.domain.plant.alternative.enums.Status;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ItemToListResponseDTO {

    private Integer id;
    private String commonName;
    private String scientificName;
    private Character scientistSurnameInitial;
    private Status status;

    @JsonProperty("family")
    private String familyName;
}

