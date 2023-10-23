package org.fdryt.ornamental.dto.auth;

public record AuthenticationRequest(
        String username,
        String password
) {}
