package org.fdryt.ornamental.domain.plant;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "images")
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
