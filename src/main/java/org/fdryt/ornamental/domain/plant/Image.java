package org.fdryt.ornamental.domain.plant;

import jakarta.persistence.*;
import lombok.*;
import org.fdryt.ornamental.dto.image.ImageMapping;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "images")
@NamedNativeQuery(
        name = "fetchAllByPlantId",
        query = """
            SELECT id, name, type
            FROM images
            WHERE plant_id = :plantId
        """,
        resultSetMapping = "ImageMapping"
)
@SqlResultSetMapping(
        name = "ImageMapping",
        classes = @ConstructorResult(
                targetClass = ImageMapping.class,
                columns = {
                        @ColumnResult(name = "id", type = String.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "type", type = String.class),
                }
        )
)
@Entity
public class Image {

    @Id
    @UuidGenerator
    @Column(updatable = false)
    private String id;

    private String name;
    private String type;
    private String path;

    @ManyToOne
    @JoinColumn(name = "plant_id")
    private Plant plant;
}
