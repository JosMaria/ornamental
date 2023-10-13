package org.fdryt.ornamental.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.plant.CreatePlantDTO;
import org.fdryt.ornamental.dto.plant.PlantResponseDTO;
import org.fdryt.ornamental.dto.plant.SimpleInfoPlantResponseDTO;
import org.fdryt.ornamental.service.PlantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        plantService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/simple-info")
    public ResponseEntity<List<SimpleInfoPlantResponseDTO>> fetchAllCommonNames() {
        return ResponseEntity.ok(plantService.getAllCommonName());
    }

    @PostMapping("/pictures")
    public ResponseEntity<String> uploadPicture(
            @RequestParam(value = "picture-one", required = false) MultipartFile pictureOne,
            @RequestParam(value = "picture-two", required = false) MultipartFile pictureTwo,
            @RequestParam(value = "picture-three", required = false) MultipartFile pictureThree
    ) {
        return new ResponseEntity<>(plantService.uploadImages(pictureOne, pictureTwo, pictureThree), CREATED);
    }

    @GetMapping("/pictures/{pictureName}")
    public ResponseEntity<byte[]> uploadPicture(@PathVariable String pictureName) {
        return ResponseEntity.ok(plantService.downloadPicture(pictureName));
    }
}
