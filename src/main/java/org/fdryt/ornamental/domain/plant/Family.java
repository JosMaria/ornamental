package org.fdryt.ornamental.domain.plant;

import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.fdryt.ornamental.dto.family.FamilyResponseDTO;

import static jakarta.persistence.GenerationType.SEQUENCE;

@NamedNativeQuery(
    name="getIdsAndNamesOfTheFamilies",
    query = """
        SELECT f.id, f.name
        FROM families f
    """,
    resultSetMapping = "FamilyResponseDTOMapping"
)
@SqlResultSetMapping(
    name="FamilyResponseDTOMapping",
    classes = @ConstructorResult(
        targetClass = FamilyResponseDTO.class,
        columns = {
            @ColumnResult(name = "id"),
            @ColumnResult(name="name")
        }
    )
)
@Getter
@Setter
@Entity
@Table(name = "families")
public class Family {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "family_sequence")
    @SequenceGenerator(name = "family_sequence", sequenceName = "family_sequence", allocationSize = 1)
    private Integer id;

    @Column(unique = true)
    private String name;
}
