package org.fdryt.ornamental.resource;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.plant.PlantRequestDTO;
import org.fdryt.ornamental.dto.alternative.PlantResponseDTO;
import org.fdryt.ornamental.service.PlantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/plants")
public class PlantResource {

    private final PlantService plantService;

    @PostMapping
    public ResponseEntity<PlantResponseDTO> save(@Valid @RequestBody PlantRequestDTO payload) {
        return ResponseEntity
                .created(URI.create("api/v2/plants"))
                .body(plantService.create(payload));
    }

    @PostMapping("image")
    public ResponseEntity<Void> uploadImage(@RequestParam("file") MultipartFile file) {
        plantService.uploadImageToFileSystem(file);
        return ResponseEntity.ok().build();
    }
}
