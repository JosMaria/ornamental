package org.fdryt.ornamental.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.fdryt.ornamental.dto.identification.ItemToListResponseDTO;

@Getter
@SuperBuilder
public class ProductResponseDTO extends ItemToListResponseDTO {

    @JsonProperty("urlPicture")
    private String firstUrlPicture;
}
