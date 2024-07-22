package org.fdryt.ornamental.domain.plant;

import jakarta.persistence.*;
import lombok.*;
import org.fdryt.ornamental.domain.plant.enums.Classification;
import org.fdryt.ornamental.domain.plant.enums.Status;
import org.fdryt.ornamental.dto.catalog.PlantCardDTO;
import org.fdryt.ornamental.dto.repertory.ItemDTO;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "plants")
@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "findAllPlantCards",
                query = """
                    SELECT plant.id, common_name, scientific_name, discoverer, status, family.name AS family_name
                    FROM plants plant
                    LEFT JOIN families family
                        ON plant.family_id = family.id
                    ORDER BY plant.common_name
                    LIMIT :limit OFFSET :offset
                """,
                resultSetMapping = "PlantCardMapping"
        ),
        @NamedNativeQuery(
                name = "findPlantCardsByClassification",
                query = """
                    SELECT plant.id, common_name, scientific_name, discoverer, status, family.name AS family_name
                    FROM plants plant
                    LEFT JOIN families family
                        ON plant.family_id = family.id
                    LEFT JOIN plant_classifications c
                        ON plant.id = c.plant_id
                    WHERE c.classifications = :classification
                    ORDER BY plant.common_name
                    LIMIT :limit OFFSET :offset
                """,
                resultSetMapping = "PlantCardMapping"
        ),
        @NamedNativeQuery(
                name = "findAllItems",
                query = """
                    SELECT common_name, scientific_name, discoverer, family.name AS family_name
                    FROM plants plant
                    LEFT JOIN families family
                    ON plant.family_id = family.id
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
public class Plant {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_families"))
    private Family family;

    @ElementCollection(targetClass = Classification.class, fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private Set<Classification> classifications;

    @OneToMany(mappedBy = "plant", fetch = FetchType.LAZY)
    private final Set<Image> images = new HashSet<>();

    public void addImage(Image image) {
        images.add(image);
    }
}
