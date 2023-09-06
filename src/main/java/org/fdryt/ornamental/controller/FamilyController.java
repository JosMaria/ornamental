package org.fdryt.ornamental.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.family.CreateFamilyDTO;
import org.fdryt.ornamental.dto.family.FamilyResponseDTO;
import org.fdryt.ornamental.service.FamilyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

// TODO: see authorizations
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/families")
public class FamilyController {

    private final FamilyService familyService;

    @PostMapping
    public ResponseEntity<FamilyResponseDTO> save(@RequestBody @Valid CreateFamilyDTO payload) {
        return new ResponseEntity<>(familyService.create(payload), CREATED);
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllNames() {
        return ResponseEntity.ok(familyService.getAllNames());
    }

    @PostMapping("/batch")
    public ResponseEntity<List<FamilyResponseDTO>> saveAll(@RequestBody List<CreateFamilyDTO> payload) {
        return new ResponseEntity<>(familyService.createAllByName(payload), CREATED);
    }
}
