package org.fdryt.ornamental.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
