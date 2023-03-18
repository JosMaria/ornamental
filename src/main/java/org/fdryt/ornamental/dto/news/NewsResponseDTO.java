package org.fdryt.ornamental.dto.news;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NewsResponseDTO {

    private Long id;
    private String urlImage;
    private String title;
    private String description;
}
