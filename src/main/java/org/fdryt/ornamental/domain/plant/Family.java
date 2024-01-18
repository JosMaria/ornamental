package org.fdryt.ornamental.domain.plant;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity
@Table(name = "families")
public class Family {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "family_sequence")
    @SequenceGenerator(name = "family_sequence", sequenceName = "family_sequence", allocationSize = 1)
    private Integer id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "fundamentalData.family")
    private final Collection<Plant> plants = new HashSet<>();
}
