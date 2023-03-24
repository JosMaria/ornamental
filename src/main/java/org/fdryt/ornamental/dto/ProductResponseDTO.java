package org.fdryt.ornamental.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fdryt.ornamental.domain.Family;
import org.fdryt.ornamental.domain.Status;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {

    private Integer id;
    private String commonName;
    private String scientificName;
    private Character plusScientificName;

    @JsonProperty("family")
    private Family familyName;

    private Status status;
}
