package org.fdryt.ornamental.domain.plant;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

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
    private FundamentalData fundamentalData;

    @Embedded
    private AdditionalData additionalData;

    @Column(length = 15)
    @Enumerated(EnumType.STRING)
    private Status status;
}
