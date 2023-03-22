package org.fdryt.ornamental.domain;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "picture_sequence")
    @SequenceGenerator(name = "picture_sequence", sequenceName = "picture_sequence", allocationSize = 1)
    private Long id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "plant_id")
    private Plant plant;
}
