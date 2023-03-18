package org.fdryt.ornamental.controller;

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
