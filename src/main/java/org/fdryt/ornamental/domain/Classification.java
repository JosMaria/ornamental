package org.fdryt.ornamental.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "classifications")
public class Classification {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "classification_sequence")
    @SequenceGenerator(name = "classification_sequence", sequenceName = "classification_sequence", allocationSize = 1)
    private Integer id;

    @Column(unique = true, length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    private ClassificationByUtility utility;
}
