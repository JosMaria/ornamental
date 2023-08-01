package org.fdryt.ornamental.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.plant.CreatePlantDTO;
import org.fdryt.ornamental.dto.MyPlantResponseDTO;
import org.fdryt.ornamental.service.PlantService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin(origins = "http://localhost:5173/", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/plants")
@PreAuthorize("hasAnyRole('ADMINISTRATOR','ASSISTANT')")
public class PlantController {

    private final PlantService plantService;

    @PostMapping
    public ResponseEntity<MyPlantResponseDTO> save(@RequestBody @Valid CreatePlantDTO payload) {
        return new ResponseEntity<>(plantService.create(payload), CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('plant:delete')")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        plantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
