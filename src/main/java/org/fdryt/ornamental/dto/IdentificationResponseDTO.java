package org.fdryt.ornamental.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class IdentificationResponseDTO {

    private Long id;
    private String commonName;
    private String scientificName;
    private Character firstLetterLastname;
    private String family;
    private String status;
}
