package org.fdryt.ornamental.domain.plant;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "details")
public class Detail {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "detail_sequence")
    @SequenceGenerator(name = "detail_sequence", sequenceName = "detail_sequence", allocationSize = 1)
    private Integer id;

    @Lob
    @Column(nullable = false)
    private String detail;

    @ManyToOne
    @JoinColumn(name = "plant_id")
    private Plant plant;
}
