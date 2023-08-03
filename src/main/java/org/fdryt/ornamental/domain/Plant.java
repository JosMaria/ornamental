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

    @Column(length = 15)
    @Enumerated(EnumType.STRING)
    private Status status;

}
