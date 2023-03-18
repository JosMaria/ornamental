package org.fdryt.ornamental.dto.news;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateNewsDTO {

    private String urlImage;
    private String title;
    private String description;
}
