package org.fdryt.ornamental.domain.plant.alternative;

import jakarta.persistence.*;
import lombok.*;
import org.fdryt.ornamental.domain.plant.alternative.enums.Status;
import org.fdryt.ornamental.dto.catalog.PlantCardDTO;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "plants_v3")
@Entity
@NamedNativeQuery(
        name = "findAllPlantCards",
        query = """
            SELECT plants.id, common_name, scientific_name, discoverer, status, families.name as family_name
            FROM plants_v3 plants
            LEFT JOIN families_v2 families
            ON plants.family_id = families.id
        """,
        resultSetMapping = "PlantCardMapping"
)
@SqlResultSetMapping(
        name = "PlantCardMapping",
        classes = @ConstructorResult(
                targetClass = PlantCardDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = String.class),
                        @ColumnResult(name = "common_name", type = String.class),
                        @ColumnResult(name = "scientific_name", type = String.class),
                        @ColumnResult(name = "discoverer", type = Character.class),
                        @ColumnResult(name = "status", type = Status.class),
                        @ColumnResult(name = "family_name", type = String.class),
                }
        )
)
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

    @Column(length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_families"))
    private FamilyV2 family;
}
