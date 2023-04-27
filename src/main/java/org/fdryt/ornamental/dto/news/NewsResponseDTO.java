package org.fdryt.ornamental.dto.news;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewsResponseDTO {

    private Integer id;
    private String urlImage;
    private String title;
    private String description;
}
