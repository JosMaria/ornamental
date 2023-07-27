package org.fdryt.ornamental.domain.plant;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ScientificName {

    @Column(name = "scientific_name")
    private String name;

    @Column(name = "initial")
    private Character scientistLastnameInitial;
}
