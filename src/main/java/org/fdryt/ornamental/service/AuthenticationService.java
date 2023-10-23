package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.auth.AuthenticationRequest;
import org.fdryt.ornamental.dto.auth.AuthenticationResponse;
import org.fdryt.ornamental.dto.auth.RegisterRequest;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
