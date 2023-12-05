package org.fdryt.ornamental.dto.user;

import org.fdryt.ornamental.domain.user.Role;

public record UserResponseDTO(
        Integer id,
        String name,
        String lastname,
        String username,
        Role role
) {}
