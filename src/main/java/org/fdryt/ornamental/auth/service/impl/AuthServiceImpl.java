package org.fdryt.ornamental.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.auth.domain.User;
import org.fdryt.ornamental.auth.dto.AuthRequestDTO;
import org.fdryt.ornamental.auth.dto.AuthResponseDTO;
import org.fdryt.ornamental.auth.dto.RegisterRequestDTO;
import org.fdryt.ornamental.auth.repository.UserRepository;
import org.fdryt.ornamental.auth.service.AuthService;
import org.fdryt.ornamental.problem.exception.EntityAlreadyException;
import org.fdryt.ornamental.security.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager manager;
    private final ModelMapper userMapper;

    @Override
    public AuthResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        if (userRepository.existsByUsername(registerRequestDTO.username())) {
            throw new EntityAlreadyException(User.class, registerRequestDTO.username());

        } else {
            User userToPersist = userMapper.map(registerRequestDTO, User.class);
            User userPersisted = userRepository.save(userToPersist);
            String tokenGenerated = jwtService.generateToken(userPersisted);

            return new AuthResponseDTO(tokenGenerated);
        }
    }

    @Override
    public AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO) {
        manager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.username(), authRequestDTO.password()));
        User userObtained = userRepository.findByUsername(authRequestDTO.username())
                .orElseThrow(() -> new UsernameNotFoundException("Credentials incorrect"));
        String tokenGenerated = jwtService.generateToken(userObtained);

        return new AuthResponseDTO(tokenGenerated);
    }
}
