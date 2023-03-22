package org.fdryt.ornamental.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "families")
public class Family {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "family_sequence")
    @SequenceGenerator(name = "family_sequence", sequenceName = "family_sequence", allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String name;

    public Family(String name) {
        this.name = name;
    }
}
