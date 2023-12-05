package org.fdryt.ornamental.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.user.User;
import org.fdryt.ornamental.dto.user.UserResponseDTO;
import org.fdryt.ornamental.repository.UserJpaRepository;
import org.fdryt.ornamental.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;

    @Override
    public List<UserResponseDTO> fetchAllUsers() {
        List<User> usersObtained = userJpaRepository.findAll();
        log.info("Users fetched.");

        return usersObtained.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public UserResponseDTO fetchUserById(final Integer id) {
        User userObtained = userJpaRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User with ID: {} not found.", id);
                    return new EntityNotFoundException("Usuario no encontrado.");
                });

        log.info("User with ID: {} founded.", userObtained.getId());
        return entityToDTO(userObtained);
    }

    private UserResponseDTO entityToDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getUsername(),
                user.getRole()
        );
    }
}
