package org.fdryt.ornamental.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "plants")
public class Plant {

    @Id
    @SequenceGenerator(name = "plant_sequence", sequenceName = "plant_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plant_sequence")
    private Long id;

    private String commonName;

    @Column(unique = true)
    private String scientificName;

    @Enumerated(EnumType.STRING)
    private TypeFamily family;

    @ManyToMany
    @JoinTable( name = "plants_classifications",
                joinColumns = @JoinColumn(name = "plant_id"),
                inverseJoinColumns = @JoinColumn(name = "classification_id"))
    private final Set<Classification> classifications = new HashSet<>();

    public void addClassification(Classification classification) {
        classifications.add(classification);
    }
}
