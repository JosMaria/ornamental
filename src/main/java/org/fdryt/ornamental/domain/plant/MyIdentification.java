package org.fdryt.ornamental.domain.plant;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class  MyIdentification {

    private String commonName;

    @Embedded
    private ScientificName scientificName;
}
