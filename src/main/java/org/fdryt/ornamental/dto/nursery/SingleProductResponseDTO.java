package org.fdryt.ornamental.dto.nursery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class SingleProductResponseDTO extends ItemToListResponseDTO {

    private Set<String> urlPictures;
    private Set<String> classifications;
}
