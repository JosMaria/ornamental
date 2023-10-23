package org.fdryt.ornamental.dto.auth;

public record RegisterRequest(
        String firstname,
        String lastname,
        String username,
        String password
) {}
