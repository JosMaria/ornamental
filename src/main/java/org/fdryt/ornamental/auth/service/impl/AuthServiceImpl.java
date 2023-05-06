package org.fdryt.ornamental.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.auth.domain.Role;
import org.fdryt.ornamental.auth.domain.User;
import org.fdryt.ornamental.auth.dto.AuthRequestDTO;
import org.fdryt.ornamental.auth.dto.AuthResponseDTO;
import org.fdryt.ornamental.auth.dto.RegisterRequestDTO;
import org.fdryt.ornamental.auth.repository.UserRepository;
import org.fdryt.ornamental.auth.service.AuthService;
import org.fdryt.ornamental.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager manager;

    @Override
    public AuthResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        if (userRepository.existsByUsername(registerRequestDTO.username())) {
            throw new IllegalArgumentException(String.format("username: %s already exists", registerRequestDTO.username()));

        } else {
            User userToPersist = registerDTOToUserEntity(registerRequestDTO);
            User userPersisted = userRepository.save(userToPersist);
            String tokenGenerated = jwtService.generateToken(userPersisted);

            return new AuthResponseDTO(tokenGenerated);
        }
    }

    @Override
    public AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO) {
        manager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        User userObtained = userRepository.findByUsername(authRequestDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Credentials incorrect"));
        String tokenGenerated = jwtService.generateToken(userObtained);

        return new AuthResponseDTO(tokenGenerated);
    }

    private User registerDTOToUserEntity(RegisterRequestDTO dto) {
        return User.builder()
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .role(Role.USER)
                .build();
    }
}
