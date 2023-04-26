package org.fdryt.ornamental.dto.news;

import jakarta.validation.constraints.NotBlank;

public record UpdateNewsDTO(
        @NotBlank(message = "URL_IMAGE field should not be null, empty or blank")
        String urlImage,

        @NotBlank(message = "TITLE field should not be null, empty or blank")
        String title,

        @NotBlank(message = "DESCRIPTION field should not be null, empty or blank")
        String description
) {}
