package org.fdryt.ornamental.domain.plant;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.Collection;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Builder
@Entity
@Table(name = "my_plants")
public class MyPlant {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "my_plant_sequence")
    @SequenceGenerator(name = "my_plant_sequence", sequenceName = "my_plant_sequence", allocationSize = 1)
    private Integer id;

    @Embedded
    private MyIdentification identification;

    private Collection<String> notes;
}
