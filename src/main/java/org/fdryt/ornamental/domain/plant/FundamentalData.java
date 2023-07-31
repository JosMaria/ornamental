package org.fdryt.ornamental.domain.plant;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Embeddable
public class FundamentalData {

    @Column(nullable = false, unique = true)
    private String commonName;

    @Embedded
    private ScientificName scientificName;

    @Enumerated(EnumType.STRING)
    private Set<Classification> classifications;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_families"))
    private MyFamily family;
}
