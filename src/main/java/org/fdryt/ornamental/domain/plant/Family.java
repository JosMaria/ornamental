package org.fdryt.ornamental.domain.plant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.*;

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
}
