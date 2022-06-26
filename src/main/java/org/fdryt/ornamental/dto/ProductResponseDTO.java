package org.fdryt.ornamental.dto;

import lombok.Getter;
import lombok.Setter;
import org.fdryt.ornamental.domain.Family;

@Getter
@Setter
public class ProductResponseDTO {

    private Long id;
    private String commonName;
    private String scientificName;
    private Family family;
    private String urlImage;
    private boolean inConservation;
}
