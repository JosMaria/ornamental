package org.fdryt.ornamental.controller;

import jakarta.validation.Valid;
import org.fdryt.ornamental.dto.auth.AuthRequestDTO;
import org.fdryt.ornamental.dto.auth.AuthResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody @Valid AuthRequestDTO authRequestDTO) {
        return null;
    }
}
