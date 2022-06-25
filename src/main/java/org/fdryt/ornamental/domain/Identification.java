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
@Table(name = "identifications")
public class Identification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "identification_generator")
    @SequenceGenerator(name = "identification_generator")
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String commonName;

    @Column(length = 50)
    private String scientificName;

    @Enumerated(EnumType.STRING)
    private Family family;

    @ManyToMany
    @JoinTable(
            name = "identifications_classifications",
            joinColumns =
                @JoinColumn(
                    foreignKey = @ForeignKey(name = "fk_identification_id"),
                    name = "identification_id"),
            inverseJoinColumns =
                @JoinColumn(
                    foreignKey = @ForeignKey(name = "fk_classification_id"),
                    name = "classification_id"))
    private final Set<Classification> classifications = new HashSet<>();

    public Identification(String commonName, String scientificName, Family family) {
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.family = family;
    }

    public void addClassification(Classification classification) {
        classifications.add(classification);
    }
}
