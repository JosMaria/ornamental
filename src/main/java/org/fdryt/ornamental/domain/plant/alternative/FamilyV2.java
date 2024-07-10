package org.fdryt.ornamental.domain.plant.alternative;

import jakarta.persistence.*;
import lombok.*;
import org.fdryt.ornamental.dto.alternative.FamilyResponseDTO;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "families_v2")
@Entity
@NamedNativeQuery(
        name = "findAllFamilies",
        query = """
            SELECT id, name
            FROM families_v2
            ORDER BY name
        """,
        resultSetMapping = "FamilyMapping"
)
@SqlResultSetMapping(
        name = "FamilyMapping",
        classes = @ConstructorResult(
                targetClass = FamilyResponseDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = String.class),
                        @ColumnResult(name = "name", type = String.class),
                }
        )
)
public class FamilyV2 {

    @Id
    @UuidGenerator
    @Column(updatable = false)
    private String id;

    @Column(unique = true)
    private String name;
}
