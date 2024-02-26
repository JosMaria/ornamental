package org.fdryt.ornamental.domain.plant;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "plants")
public class Plant {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "plant_sequence")
    @SequenceGenerator(name = "plant_sequence", sequenceName = "plant_sequence", allocationSize = 1)
    private Integer id;

    @Embedded
    private FundamentalData fundamentalData;

    @Column(length = 15)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Lob
    private String description;

    private Double price;

    @OneToMany(mappedBy = "plant", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private final Collection<Detail> details = new ArrayList<>();

    @OneToMany(mappedBy = "plant", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private final Collection<TechnicalSheet> technicalSheets = new ArrayList<>();

    @OneToMany(mappedBy = "plant", fetch = FetchType.EAGER)
    private final Set<Picture> pictures = new HashSet<>();

    public void addDetails(Collection<Detail> newDetails) {
        details.addAll(newDetails);
    }

    public void addTechnicalSheet(Collection<TechnicalSheet> newTechnicalSheet) {
        technicalSheets.addAll(newTechnicalSheet);
    }

    public void addPicture(Picture picture) {
        pictures.add(picture);
    }
}
