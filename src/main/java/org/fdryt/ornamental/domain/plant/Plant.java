package org.fdryt.ornamental.domain.plant;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Builder
@Entity
@Table(name = "plants")
public class Plant {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "plant_sequence")
    @SequenceGenerator(name = "plant_sequence", sequenceName = "plant_sequence", allocationSize = 1)
    private Integer id;

    @Embedded
    private FundamentalData fundamentalData;

    @Embedded
    private AdditionalData additionalData;

    @Column(length = 15)
    @Enumerated(EnumType.STRING)
    private Status status;
}
