package org.fdryt.ornamental.service.impl;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.user.Role;
import org.fdryt.ornamental.domain.user.User;
import org.fdryt.ornamental.dto.auth.AuthenticationRequest;
import org.fdryt.ornamental.dto.auth.AuthenticationResponse;
import org.fdryt.ornamental.dto.auth.RegisterRequest;
import org.fdryt.ornamental.repository.UserJpaRepository;
import org.fdryt.ornamental.service.AuthenticationService;
import org.fdryt.ornamental.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtUtils jwtUtils;
    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(final RegisterRequest request) {
        if (userJpaRepository.existsByUsername(request.username())) {
            log.error("Username {} already exists.", request.username());
            throw new EntityExistsException("Nombre de usuario %s ya existe.".formatted(request.username()));
        }

        // TODO: do given Role and its permissions
        User user = User.builder()
                .name(request.name())
                .lastName(request.lastname())
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.ASSISTANT)
                .build();

        User userPersisted = userJpaRepository.save(user);
        log.info("User {} created successfully.", userPersisted.getUsername());
        return new AuthenticationResponse(jwtUtils.generateToken(userPersisted));
    }

    @Override
    public AuthenticationResponse authenticate(final AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        User userObtained = userJpaRepository.findByUsername(request.username())
                .orElseThrow(() -> new UsernameNotFoundException("User %s not found.".formatted(request.username())));
        log.info("User {} authenticate successfully", userObtained.getUsername());
        Map<String, Object> claims =  Map.of("role", userObtained.getRole());
        String jwtTokenGenerated = jwtUtils.generateToken(claims, userObtained);
        return new AuthenticationResponse(jwtTokenGenerated);
    }
}
