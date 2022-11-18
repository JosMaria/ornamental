package org.fdryt.ornamental.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class IdentificationResponseDTO {

    private Long id;
    private String commonName;
    private String scientificName;
    private String firstLetterLastname;
    private String family;
    private String status;
}
