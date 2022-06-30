package org.fdryt.ornamental.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity
@Table(name = "identifications")
public class Identification {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "identification_generator")
    @SequenceGenerator(name = "identification_generator")
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String commonName;

    @Column(length = 50)
    private String scientificName;

    @Enumerated(STRING)
    private Family family;

    @JoinTable(
            name = "identifications_classifications",
            joinColumns =
                @JoinColumn(
                    foreignKey = @ForeignKey(name = "fk_identification"),
                    name = "identification_id"),
            inverseJoinColumns =
                @JoinColumn(
                    foreignKey = @ForeignKey(name = "fk_classification"),
                    name = "classification_id"))
    @ManyToMany(fetch = LAZY)
    private final Set<Classification> classificationsByUtility = new HashSet<>();
}
