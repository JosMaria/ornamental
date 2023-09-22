package org.fdryt.ornamental.domain.plant;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class ScientificName {

    @Column(name = "scientific_name")
    private String name;

    @Column(name = "initial")
    private Character scientistLastnameInitial;

    @Override
    public String toString() {
        return "%s %s".formatted(name, scientistLastnameInitial);
    }
}
