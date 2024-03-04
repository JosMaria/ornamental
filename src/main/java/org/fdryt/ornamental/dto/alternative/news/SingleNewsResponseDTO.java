package org.fdryt.ornamental.dto.alternative.news;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@JsonInclude(NON_DEFAULT)
public record SingleNewsResponseDTO(
        String id,
        String title,
        String description,
        String content,
        String urlImage,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String urlPhotoProfile,
        String authorName
) {}
