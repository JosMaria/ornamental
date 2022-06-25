package org.fdryt.ornamental.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "classifications")
public class Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classification_sequence")
    @SequenceGenerator(name = "classification_sequence", sequenceName = "classification_sequence", allocationSize = 1)
    private Long id;

    @Column(unique = true, length = 50)
    @Enumerated(EnumType.STRING)
    private ClassificationByUtility classificationByUtility;

    @ManyToMany(mappedBy = "classifications")
    private final Set<Identification> identifications = new HashSet<>();
}
