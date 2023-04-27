package org.fdryt.ornamental.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.plant.CreatePlantDTO;
import org.fdryt.ornamental.dto.plant.PlantResponseDTO;
import org.fdryt.ornamental.dto.product.ProductResponseDTO;
import org.fdryt.ornamental.dto.product.ItemToListResponseDTO;
import org.fdryt.ornamental.service.PlantService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin(origins = "http://localhost:5173/", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/plants")
public class PlantController {

    private final PlantService plantService;

    // TODO: I should talk about count of products in number page
    @PostMapping
    public ResponseEntity<PlantResponseDTO> create(@RequestBody @Valid CreatePlantDTO createPlantDTO) {
        return new ResponseEntity<>(plantService.create(createPlantDTO), CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        plantService.delete(id);
        return ResponseEntity.noContent().build();
    }
/*
    @GetMapping("types/{type}")
    public ResponseEntity<List<ProductResponseDTO>> findAllByClassification(@PageableDefault(size = 6) Pageable pageable, @PathVariable("type") String type) {
        return ResponseEntity.ok(plantService.findAllByClassification(type, pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResponseDTO> findProductById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(plantService.findProductById(id));
    }

    @GetMapping("classifications/{classification}")
    public ResponseEntity<List<ProductResponseDTO>> findAllOrnamentalPlantsByClassification(
            @PathVariable("classification") String classification,
            @PageableDefault(size = 16, direction = ASC, sort = "identification.commonName") Pageable pageable) {
        return ResponseEntity.ok(plantService.findAllOrnamentalPlantsByClassification(classification, pageable));
    }*/
}
