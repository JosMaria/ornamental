package org.fdryt.ornamental.dto.family;

import jakarta.validation.constraints.NotBlank;

public record CreateFamilyDTO (
    @NotBlank(message = "NAME field should not be null, empty or blank")
    String name
) {}
