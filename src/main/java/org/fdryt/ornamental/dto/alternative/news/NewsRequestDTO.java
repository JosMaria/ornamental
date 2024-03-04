package org.fdryt.ornamental.dto.alternative.news;

import org.hibernate.validator.constraints.Length;

public record NewsRequestDTO(
        String title,

        @Length(max = 255, message = "message to limit max excess in description news")
        String description,

        String content
) {}
