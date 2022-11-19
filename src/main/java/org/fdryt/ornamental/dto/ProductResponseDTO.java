package org.fdryt.ornamental.dto;

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

    private Long id;
    private String commonName;
    private String scientificName;
    private Family family;
    private String urlImage;
    private Status status;
}
