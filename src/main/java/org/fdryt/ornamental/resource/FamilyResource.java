package org.fdryt.ornamental.resource;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.family.FamilyRequestDTO;
import org.fdryt.ornamental.dto.family.FamilyResponseDTO;
import org.fdryt.ornamental.service.FamilyService;
import org.fdryt.ornamental.utils.ValidSet;
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
    public ResponseEntity<FamilyResponseDTO> save(@Valid @RequestBody FamilyRequestDTO payload) {
        return ResponseEntity
                .created(URI.create("/api/v2/families"))
                .body(familyService.create(payload));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<FamilyResponseDTO>> saveMany(@Valid @RequestBody ValidSet<FamilyRequestDTO> payload) {
        return ResponseEntity
                .created(URI.create("/api/v2/families/batch"))
                .body(familyService.createMany(payload));
    }

    @GetMapping
    public ResponseEntity<List<FamilyResponseDTO>> fetchAll() {
        return ResponseEntity.ok(familyService.obtainAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FamilyResponseDTO> removeByID(@PathVariable("id") String id) {
        return ResponseEntity.ok(familyService.deleteByID(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FamilyResponseDTO> updateNameByID(
            @PathVariable("id") String id,
            @Valid @RequestBody FamilyRequestDTO payload
    ) {
        return ResponseEntity.ok(familyService.modifyNameByID(id, payload));
    }
}
