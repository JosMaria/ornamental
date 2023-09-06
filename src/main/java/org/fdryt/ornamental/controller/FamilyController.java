package org.fdryt.ornamental.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.family.CreateFamilyDTO;
import org.fdryt.ornamental.dto.family.FamilyResponseDTO;
import org.fdryt.ornamental.service.FamilyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/families")
public class FamilyController {

    private final FamilyService familyService;

    @PostMapping("/batch")
    public ResponseEntity<List<FamilyResponseDTO>> saveAll(@RequestBody @Valid List<CreateFamilyDTO> payload) {
        return new ResponseEntity<>(familyService.createAllByName(payload), CREATED);
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> fetchAllName() {
        return ResponseEntity.ok(familyService.getAllNames());
    }
}
