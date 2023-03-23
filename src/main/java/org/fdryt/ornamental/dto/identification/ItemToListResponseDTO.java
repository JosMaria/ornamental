package org.fdryt.ornamental.dto.identification;

import lombok.Builder;
import lombok.Getter;
import org.fdryt.ornamental.domain.Status;

@Getter
@Builder
public class ItemToListResponseDTO {

    private Integer id;
    private String commonName;
    private String scientificName;
    private Character plusScientificName;
    private String familyName;
    private Status status;
}
