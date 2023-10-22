package org.fdryt.ornamental.service.impl;

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

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtUtils jwtUtils;
    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .firstname(request.firstname())
                .lastName(request.lastname())
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.ASSISTANT)
                .build();

        User userPersisted = userJpaRepository.save(user);
        log.info("User {} created successfully.", userPersisted.getUsername());
        String jwtTokenGenerated = jwtUtils.generateToken(userPersisted);

        return new AuthenticationResponse(jwtTokenGenerated);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        User userObtained = userJpaRepository.findByUsername(request.username())
                .orElseThrow(() -> new UsernameNotFoundException("User %s not found.".formatted(request.username())));

        String jwtTokenGenerated = jwtUtils.generateToken(userObtained);

        return new AuthenticationResponse(jwtTokenGenerated);
    }
}
