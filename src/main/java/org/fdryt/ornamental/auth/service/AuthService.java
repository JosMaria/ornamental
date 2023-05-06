package org.fdryt.ornamental.auth.service;

import org.fdryt.ornamental.auth.dto.AuthRequestDTO;
import org.fdryt.ornamental.auth.dto.AuthResponseDTO;
import org.fdryt.ornamental.auth.dto.RegisterRequestDTO;

public interface AuthService {

    AuthResponseDTO register(RegisterRequestDTO registerRequestDTO);

    AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO);
}
