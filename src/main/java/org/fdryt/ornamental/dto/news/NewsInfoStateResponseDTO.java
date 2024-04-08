package org.fdryt.ornamental.dto.news;

import java.time.LocalDateTime;

public record NewsInfoStateResponseDTO(
        String id,
        String title,
        String description,
        boolean isVisible,
        LocalDateTime createdAt
) {}
