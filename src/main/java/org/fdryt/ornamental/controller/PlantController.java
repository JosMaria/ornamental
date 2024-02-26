package org.fdryt.ornamental.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.plant.CreatePlantDTO;
import org.fdryt.ornamental.dto.plant.PlantResponseDTO;
import org.fdryt.ornamental.dto.plant.SimpleInfoPlantResponseDTO;
import org.fdryt.ornamental.dto.plant.UpdateInformationBasicDTO;
import org.fdryt.ornamental.service.PlantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin(origins = {"http://localhost:5173/", "https://magnificent-daifuku-409cab.netlify.app/"}, allowedHeaders = "*")
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

    @PostMapping("/picture/{plantId}")
    public ResponseEntity<String> uploadPicture(@RequestParam(value = "picture") MultipartFile file, @PathVariable("plantId") Integer plantId) {
        return new ResponseEntity<>(plantService.uploadImageToFileSystem(file, plantId), CREATED);
    }

    @GetMapping("/picture/{pictureName}")
    public ResponseEntity<?> uploadPicture(@PathVariable String pictureName) {
        return ResponseEntity.ok(plantService.downloadPictureFromFileSystem(pictureName));
    }

    @PatchMapping("/{id}/information-basic")
    public ResponseEntity<?> updateInformationBasic(@PathVariable("id") Integer id, @RequestBody UpdateInformationBasicDTO payload) {
        return ResponseEntity.ok(plantService.updateInformationBasic(id, payload));
    }

    @PutMapping
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(plantService.uploadPhoto(file));
    }
}
