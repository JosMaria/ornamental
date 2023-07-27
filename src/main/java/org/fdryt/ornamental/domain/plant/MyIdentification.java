package org.fdryt.ornamental.domain.plant;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class  MyIdentification {

    private String commonName;

    @Embedded
    private ScientificName scientificName;
}

