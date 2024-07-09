package org.fdryt.ornamental.domain.plant.alternative;

import jakarta.persistence.*;
import lombok.*;
import org.fdryt.ornamental.domain.plant.alternative.enums.Classification;
import org.fdryt.ornamental.domain.plant.alternative.enums.Status;
import org.fdryt.ornamental.dto.catalog.PlantCardDTO;
import org.fdryt.ornamental.dto.repertory.ItemDTO;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "plants_v3")
@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "findAllPlantCards",
                query = """
                    SELECT plants.id, common_name, scientific_name, discoverer, status, families.name AS family_name
                    FROM plants_v3 plants
                    LEFT JOIN families_v2 families
                        ON plants.family_id = families.id
                    ORDER BY plants.common_name
                    LIMIT :limit OFFSET :offset
                """,
                resultSetMapping = "PlantCardMapping"
        ),
        @NamedNativeQuery(
                name = "findPlantCardsByClassification",
                query = """
                    SELECT plants.id, common_name, scientific_name, discoverer, status, families.name AS family_name
                    FROM plants_v3 plants
                    LEFT JOIN families_v2 families
                        ON plants.family_id = families.id
                    LEFT JOIN plantv3_classifications c
                        ON plants.id = c.plantv3_id
                    WHERE c.classifications = :classification
                    ORDER BY plants.common_name
                    LIMIT :limit OFFSET :offset
                """,
                resultSetMapping = "PlantCardMapping"
        ),
        @NamedNativeQuery(
                name = "findAllItems",
                query = """
                    SELECT common_name, scientific_name, discoverer, families.name AS family_name
                    FROM plants_v3 plants
                    LEFT JOIN families_v2 families
                    ON plants.family_id = families.id
                """,
                resultSetMapping = "ItemMapping"
        )
})
@SqlResultSetMappings({
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
        ),
        @SqlResultSetMapping(
                name = "ItemMapping",
                classes = @ConstructorResult(
                        targetClass = ItemDTO.class,
                        columns = {
                                @ColumnResult(name = "common_name", type = String.class),
                                @ColumnResult(name = "scientific_name", type = String.class),
                                @ColumnResult(name = "discoverer", type = Character.class),
                                @ColumnResult(name = "family_name", type = String.class),
                        }
                )
        )
})
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

    @ElementCollection(targetClass = Classification.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Classification> classifications;
}
