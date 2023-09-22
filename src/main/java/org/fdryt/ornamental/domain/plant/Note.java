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
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "note_sequence")
    @SequenceGenerator(name = "note_sequence", sequenceName = "note_sequence", allocationSize = 1)
    private Integer id;

    @Lob
    @Column(nullable = false)
    private String note;

    @ManyToOne
    @JoinColumn(name = "plant_id")
    private Plant plant;
}
