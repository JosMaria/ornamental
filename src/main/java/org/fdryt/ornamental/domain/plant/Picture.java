package org.fdryt.ornamental.domain.plant;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "picture_sequence")
    @SequenceGenerator(name = "picture_sequence", sequenceName = "picture_sequence", allocationSize = 1)
    private Integer id;

    @Column(unique = true)
    private String name;

    private String type;
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "plant_id")
    private Plant plant;
}
