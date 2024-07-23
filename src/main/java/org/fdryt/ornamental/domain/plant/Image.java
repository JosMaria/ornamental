package org.fdryt.ornamental.domain.plant;

import jakarta.persistence.*;
import lombok.*;
import org.fdryt.ornamental.dto.image.ImageMapping;

import static jakarta.persistence.GenerationType.SEQUENCE;

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
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "type", type = String.class),
                }
        )
)
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "image_sequence")
    @SequenceGenerator(name = "image_sequence", sequenceName = "image_sequence", allocationSize = 1)
    private Long id;

    private String name;
    private String type;
    private String path;

    @ManyToOne
    @JoinColumn(name = "plant_id")
    private Plant plant;
}
