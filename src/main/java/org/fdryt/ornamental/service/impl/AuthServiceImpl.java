package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.domain.user.User;
import org.fdryt.ornamental.dto.auth.AuthRequestDTO;
import org.fdryt.ornamental.dto.auth.AuthResponseDTO;
import org.fdryt.ornamental.repository.UserJpaRepository;
import org.fdryt.ornamental.service.AuthService;
import org.fdryt.ornamental.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserJpaRepository userJpaRepository;
    private final JwtService jwtService;

    @Override
    public AuthResponseDTO authenticate(final AuthRequestDTO authRequestDTO) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(authRequestDTO.username(), authRequestDTO.password());
        authenticationManager.authenticate(authToken);

        User userObtained = userJpaRepository.findByUsername(authRequestDTO.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username %s not found.".formatted(authRequestDTO.username())));

        String tokenGenerated = jwtService.generateToken(userObtained, generateExtraClaims(userObtained));

        return new AuthResponseDTO(tokenGenerated);
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole().name());

        return extraClaims;
    }
}
