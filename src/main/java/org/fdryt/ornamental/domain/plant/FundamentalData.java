package org.fdryt.ornamental.domain.plant;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.fdryt.ornamental.domain.plant.alternative.enums.Classification;

import java.util.Set;

@Getter
@Setter
@Embeddable
public class FundamentalData {

    @Column(nullable = false, unique = true)
    private String commonName;

    @Embedded
    private ScientificName scientificName;

    @ElementCollection(targetClass = Classification.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Classification> classifications;

    @ManyToOne
    private Family family;
}
