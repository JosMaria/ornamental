package org.fdryt.ornamental.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.plant.CreatePlantDTO;
import org.fdryt.ornamental.dto.plant.PlantResponseDTO;
import org.fdryt.ornamental.service.PlantService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin(origins = "http://localhost:5173/", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/plants")
public class PlantController {

    private final PlantService plantService;

    @PostMapping
    public ResponseEntity<PlantResponseDTO> save(@RequestBody @Valid CreatePlantDTO payload) {
        return new ResponseEntity<>(plantService.create(payload), CREATED);
    }

    // TODO: Method test, this will delete
    @PostMapping("/batch")
    public ResponseEntity<List<PlantResponseDTO>> saveAll(@RequestBody List<CreatePlantDTO> payload) {
        return ResponseEntity.ok(plantService.createAll(payload));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('plant:delete')")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        plantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
