package org.fdryt.ornamental.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(

        @NotBlank(message = "FIRSTNAME field should not be null, empty or blank")
        String firstname,

        @NotBlank(message = "LASTNAME field should not be null, empty or blank")
        String lastname,

        @NotBlank(message = "USERNAME field should not be null, empty or blank")
        String username,

        @NotBlank(message = "PASSWORD field should not be null, empty or blank")
        String password
) {}
