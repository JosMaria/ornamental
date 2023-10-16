package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.auth.AuthRequestDTO;
import org.fdryt.ornamental.dto.auth.AuthResponseDTO;

public interface AuthService {

    AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO);
}
