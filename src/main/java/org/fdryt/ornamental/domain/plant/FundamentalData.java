package org.fdryt.ornamental.domain.plant;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_families"))
    private Family family;
}
