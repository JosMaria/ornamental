package org.fdryt.ornamental.controller;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.family.CreateFamilyDTO;
import org.fdryt.ornamental.dto.family.FamilyResponseDTO;
import org.fdryt.ornamental.service.FamilyService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/families")
@PreAuthorize("hasAnyRole('ADMINISTRATOR','ASSISTANT')")
public class FamilyController {

    private final FamilyService familyService;

    @PostMapping
    @PreAuthorize("hasAuthority('family:create')")
    public ResponseEntity<FamilyResponseDTO> create(@RequestBody CreateFamilyDTO createFamilyDTO) {
        return ResponseEntity.ok(familyService.create(createFamilyDTO));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('family:read')")
    public ResponseEntity<Set<String>> findAll() {
        return ResponseEntity.ok(familyService.findAll());
    }
}
