package org.fdryt.ornamental.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.GenerationType.SEQUENCE;
import static org.hibernate.annotations.OnDeleteAction.CASCADE;

@Getter
@Setter
@Entity
@Table(name = "ornamental_plants")
public class OrnamentalPlant {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "ornamental_plant_sequence")
    @SequenceGenerator(name = "ornamental_plant_sequence", sequenceName = "ornamental_plant_sequence", allocationSize = 1)
    private Long id;

    @OnDelete(action = CASCADE)
    @JoinColumn(
            nullable = false, unique = true,
            foreignKey = @ForeignKey(name = "fk_identification"))
    @OneToOne(cascade = PERSIST)
    private Identification identification;

    private String urlImage;
    private boolean inConservation;
    /*private String origin;
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
    private String others;*/
}
