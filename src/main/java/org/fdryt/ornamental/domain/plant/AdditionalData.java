package org.fdryt.ornamental.domain.plant;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Embeddable
public class AdditionalData {

    private Collection<String> notes;
    private Collection<String> details;

}
