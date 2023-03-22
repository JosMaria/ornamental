package org.fdryt.ornamental.dto.identification;

import lombok.Builder;
import org.fdryt.ornamental.domain.Status;

@Builder
public class IdentificationResponseDTO {

    private Integer id;
    private String commonName;
    private String scientificName;
    private Character plusScientificName;
    private String familyName;
    private Status status;
}
