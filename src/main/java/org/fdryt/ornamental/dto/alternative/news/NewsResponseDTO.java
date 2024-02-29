package org.fdryt.ornamental.dto.alternative.news;

import java.time.LocalDateTime;

public record NewsResponseDTO(String id, String title, String content, LocalDateTime published) {}
