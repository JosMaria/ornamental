package org.fdryt.ornamental.auth.dto;

import org.fdryt.ornamental.auth.domain.Role;

public record RegisterRequestDTO(String username, String password, Role role) { }
