package org.fdryt.ornamental.domain.plant;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
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

    @OneToMany(mappedBy = "plant")
    private Set<Note> notes = new HashSet<>();

    @OneToMany(mappedBy = "plant")
    private Set<Detail> details = new HashSet<>();

    @OneToMany(mappedBy = "plant")
    private Set<TechnicalSheet> technicalSheets = new HashSet<>();
}
