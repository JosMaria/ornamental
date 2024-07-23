package org.fdryt.ornamental.resource;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.plant.PlantRequestDTO;
import org.fdryt.ornamental.dto.plant.PlantResponseDTO;
import org.fdryt.ornamental.service.PlantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @PostMapping(value = "{plantId}/image", consumes = {"multipart/form-data"})
    public ResponseEntity<Void> uploadImage(@PathVariable("plantId") String plantId, @RequestPart("image") MultipartFile file) {
        plantService.uploadImageToFileSystem(plantId, file);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{plantId}/image")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String plantId) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG)
                .body(plantService.downloadImageFromFileSystem(plantId));
    }
}
