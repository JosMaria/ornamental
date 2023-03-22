package org.fdryt.ornamental.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity
@Table(name = "identifications")
public class Identification {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "identification_sequence")
    @SequenceGenerator(name = "identification_sequence", sequenceName = "identification_sequence", allocationSize = 1)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String commonName;

    @Column(length = 50)
    private String scientificName;

    @ManyToOne
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_families"))
    private Family family;

    @ManyToMany(fetch = LAZY)
    @JoinTable(
        name = "identifications_classifications",
        joinColumns = @JoinColumn(name = "identification_id", foreignKey = @ForeignKey(name = "fk_identification")),
        inverseJoinColumns = @JoinColumn(name = "classification_id"), foreignKey = @ForeignKey(name = "fk_classification")
    )
    private final Set<Classification> classifications = new HashSet<>();
}
