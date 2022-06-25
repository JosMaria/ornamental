package org.fdryt.ornamental.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ornamental_plants")
public class OrnamentalPlant {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "ornamental_plant_sequence")
    @SequenceGenerator(name = "ornamental_plant_sequence", sequenceName = "ornamental_plant_sequence", allocationSize = 1)
    private Long id;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(
            nullable = false, unique = true,
            foreignKey = @ForeignKey(name = "fk_identification_id"))
    @OneToOne(cascade = CascadeType.PERSIST)
    private Identification identification;

    private String urlImage;
    private Boolean inConservation;
    private String origin;
    private String description;
    private String size;
    private String flowering;
    private String location;
    private String soil;
    private String fertilization;
    private String pruning;
    private String diseases;
    private String sanitaryTreatments;
    private String propagation;
    private String others;
}
