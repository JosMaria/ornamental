package org.fdryt.ornamental.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "families")
public class Family {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "family_sequence")
    @SequenceGenerator(name = "family_sequence", sequenceName = "family_sequence", allocationSize = 1)
    private Integer id;

    @Column(unique = true, length = 50)
    private String name;

    public Family(String name) {
        this.name = name;
    }
}
