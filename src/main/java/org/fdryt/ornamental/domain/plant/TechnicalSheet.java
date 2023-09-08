package org.fdryt.ornamental.domain.plant;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(
    name = "technical_sheets",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"plant_id", "word"})
    }
)
public class TechnicalSheet {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "technical_sheet_sequence")
    @SequenceGenerator(name = "technical_sheet_sequence", sequenceName = "technical_sheet_sequence", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "plant_id", nullable = false)
    private Plant plant;

    @Column(nullable = false)
    private String word;

    @Lob
    @Column(nullable = false)
    private String content;
}
