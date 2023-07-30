package org.fdryt.ornamental.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.family.CreateFamilyDTO;
import org.fdryt.ornamental.dto.family.FamilyResponseDTO;
import org.fdryt.ornamental.service.MyFamilyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/my_families")
public class MyFamilyController {

    private final MyFamilyService familyService;

    @PostMapping
    public ResponseEntity<FamilyResponseDTO> save(@RequestBody @Valid CreateFamilyDTO payload) {
        return new ResponseEntity<>(familyService.create(payload), CREATED);
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllNames() {
        return ResponseEntity.ok(familyService.getAllNames());
    }

    @PostMapping("/batch")
    public ResponseEntity<Set<FamilyResponseDTO>> saveAll(@RequestBody Set<String> names) {
        return new ResponseEntity<>(familyService.createAllByName(names), CREATED);
    }
}
