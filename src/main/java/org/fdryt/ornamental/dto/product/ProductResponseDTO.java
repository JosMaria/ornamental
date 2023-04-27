package org.fdryt.ornamental.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fdryt.ornamental.dto.product.ItemToListResponseDTO;

@Getter
@SuperBuilder
public class ProductResponseDTO extends ItemToListResponseDTO {

    @JsonProperty("urlPicture")
    private String firstUrlPicture;
}
