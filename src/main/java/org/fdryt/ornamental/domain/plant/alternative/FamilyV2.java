package org.fdryt.ornamental.domain.plant.alternative;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "families_v2")
public class FamilyV2 {

    @Id
    @UuidGenerator
    @Column(updatable = false)
    private String id;

    @Column(unique = true)
    private String name;
}
