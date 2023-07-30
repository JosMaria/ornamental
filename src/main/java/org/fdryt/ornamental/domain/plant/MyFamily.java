package org.fdryt.ornamental.domain.plant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity
@Table(name = "my_families")
public class MyFamily {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "my_family_sequence")
    @SequenceGenerator(name = "my_family_sequence", sequenceName = "my_family_sequence", allocationSize = 1)
    private Integer id;

    @Column(unique = true, length = 50)
    private String name;

    public MyFamily(String name) {
        this.name = name;
    }
}
