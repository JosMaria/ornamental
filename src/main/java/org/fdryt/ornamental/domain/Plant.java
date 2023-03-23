package org.fdryt.ornamental.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.GenerationType.SEQUENCE;
import static org.hibernate.annotations.OnDeleteAction.CASCADE;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "plants")
public class Plant {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "plant_sequence")
    @SequenceGenerator(name = "plant_sequence", sequenceName = "plant_sequence", allocationSize = 1)
    private Integer id;

    @OnDelete(action = CASCADE)
    @OneToOne(cascade = PERSIST)
    @JoinColumn(name = "identification_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "fk_identifications"))
    private Identification identification;

    @Column(length = 15)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "plant")
    private final Set<Picture> pictures = new HashSet<>();

    public Plant(Identification identification, Status status) {
        this.identification = identification;
        this.status = status;
    }
}
