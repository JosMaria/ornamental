package org.fdryt.ornamental.controller;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.user.UserResponseDTO;
import org.fdryt.ornamental.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173/", "https://magnificent-daifuku-409cab.netlify.app/"}, allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> fetchAllUsers() {
        return ResponseEntity.ok(userService.fetchAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> fetchUserById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(userService.fetchUserById(id));
    }
}
