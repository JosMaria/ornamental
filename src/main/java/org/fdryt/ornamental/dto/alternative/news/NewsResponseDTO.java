package org.fdryt.ornamental.dto.alternative.news;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@JsonInclude(NON_DEFAULT)
public record NewsResponseDTO(
        String id,
        String title,
        String content,
        LocalDateTime createAt,
        LocalDateTime updatedAt
) {}
