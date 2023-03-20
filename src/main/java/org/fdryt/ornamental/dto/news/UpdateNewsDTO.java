package org.fdryt.ornamental.dto.news;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateNewsDTO {

    private String urlImage;

    @NotBlank(message = "TITLE field should not be null, empty or blank")
    private String title;

    @NotBlank(message = "DESCRIPTION field should not be null, empty or blank")
    private String description;
}
