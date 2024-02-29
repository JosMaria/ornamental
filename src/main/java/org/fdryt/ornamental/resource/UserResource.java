package org.fdryt.ornamental.resource;

import org.fdryt.ornamental.dto.user.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2/users")
public class UserResource {

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> fetchUsers() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> fetchUserByID(@PathVariable("id") String id) {
        return ResponseEntity.ok(null);
    }
}
