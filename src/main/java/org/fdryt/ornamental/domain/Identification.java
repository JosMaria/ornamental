package org.fdryt.ornamental.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.SEQUENCE;

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
