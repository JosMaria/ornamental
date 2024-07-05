package org.fdryt.ornamental.domain.plant.alternative;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "plants_v3")
public class PlantV3 {

    @Id
    @UuidGenerator
    @Column(updatable = false)
    private String id;

    @Column(unique = true, nullable = false, length = 50)
    private String commonName;

    @Column(length = 50)
    private String scientificName;

    private Character discoverer;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_families"))
    private FamilyV2 family;
}
