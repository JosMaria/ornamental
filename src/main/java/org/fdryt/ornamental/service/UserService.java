package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.user.UserResponseDTO;

import java.util.List;

public interface UserService {

    List<UserResponseDTO> fetchAllUsers();
}
