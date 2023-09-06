package org.fdryt.ornamental.dto.nursery;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ProductResponseDTO extends ItemToListResponseDTO {

    @JsonProperty("urlPicture")
    private String firstUrlPicture;
}
