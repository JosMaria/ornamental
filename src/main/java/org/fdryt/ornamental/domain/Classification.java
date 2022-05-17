package org.fdryt.ornamental.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "classifications")
@NoArgsConstructor
public class Classification {

    @Id
    @SequenceGenerator(name = "classification_sequence", sequenceName = "classification_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classification_sequence")
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeClassification typeClassification;
}
