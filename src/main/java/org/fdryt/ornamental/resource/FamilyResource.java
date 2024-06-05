package org.fdryt.ornamental.resource;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.alternative.FamilyRequestDTO;
import org.fdryt.ornamental.dto.alternative.FamilyResponseDTO;
import org.fdryt.ornamental.service.FamilyService;
import org.fdryt.ornamental.utils.ValidList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/families")
public class FamilyResource {

    private final FamilyService familyService;

    @PostMapping
    public ResponseEntity<FamilyResponseDTO> saveFamily(@Valid @RequestBody FamilyRequestDTO payload) {
        return ResponseEntity
                .created(URI.create("/api/v2/families"))
                .body(familyService.createFamily(payload));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<FamilyResponseDTO>> saveFamilies(@RequestBody @Valid ValidList<FamilyRequestDTO> payload) {
        return ResponseEntity
                .created(URI.create("/api/v2/families/batch"))
                .body(familyService.createFamilies(payload));
    }

    @GetMapping
    public ResponseEntity<List<FamilyResponseDTO>> fetchFamilies() {
        return ResponseEntity.ok(familyService.obtainFamilies());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FamilyResponseDTO> removeFamilyByID(@PathVariable("id") String id) {
        return ResponseEntity.ok(familyService.deleteFamilyByID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FamilyResponseDTO> updateFamilyByID(
            @PathVariable("id") String id,
            @RequestBody @Valid FamilyResponseDTO payload
    ) {

        return ResponseEntity.ok(familyService.modifyFamilyByID(id, payload));
    }
}
