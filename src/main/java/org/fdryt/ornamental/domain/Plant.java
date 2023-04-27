package org.fdryt.ornamental.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "plants")
public class Plant {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "plant_sequence")
    @SequenceGenerator(name = "plant_sequence", sequenceName = "plant_sequence", allocationSize = 1)
    private Integer id;

    @OneToOne(cascade = { PERSIST }, orphanRemoval = true)
    @JoinColumn(name = "identification_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "fk_identifications"))
    private Identification identification;

    @Column(length = 15)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "plant", fetch = FetchType.EAGER)
    private final Set<Picture> pictures = new HashSet<>();

    public Plant(Identification identification, Status status) {
        this.identification = identification;
        this.status = status;
    }
}
