package org.fdryt.ornamental.controller;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.family.CreateFamilyDTO;
import org.fdryt.ornamental.dto.family.FamilyResponseDTO;
import org.fdryt.ornamental.service.FamilyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "http://localhost:5173/", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/families")
public class FamilyController {

    private final FamilyService familyService;

    @PostMapping
    public ResponseEntity<FamilyResponseDTO> create(@RequestBody CreateFamilyDTO createFamilyDTO) {
        return ResponseEntity.ok(familyService.create(createFamilyDTO));
    }

    @GetMapping
    public ResponseEntity<Set<String>> findAll() {
        return ResponseEntity.ok(familyService.findAll());
    }
}
