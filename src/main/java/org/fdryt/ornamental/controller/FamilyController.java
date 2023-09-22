package org.fdryt.ornamental.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.family.CreateFamilyDTO;
import org.fdryt.ornamental.dto.family.FamilyResponseDTO;
import org.fdryt.ornamental.service.FamilyService;
import org.fdryt.ornamental.utils.ValidList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin(origins = "http://localhost:5173/", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/families")
public class FamilyController {

    private final FamilyService familyService;

    @PostMapping("/batch")
    public ResponseEntity<List<FamilyResponseDTO>> saveAll(@RequestBody @Valid ValidList<CreateFamilyDTO> payload) {
        return new ResponseEntity<>(familyService.createAll(payload), CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FamilyResponseDTO>> fetchAllFamilies() {
        return ResponseEntity.ok(familyService.getAllFamilies());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        familyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FamilyResponseDTO> update(@PathVariable("id") Integer id, @RequestBody @Valid CreateFamilyDTO payload) {
        return ResponseEntity.ok(familyService.updateName(id, payload));
    }
}
