package org.fdryt.ornamental.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.CreatePlantDTO;
import org.fdryt.ornamental.dto.ProductResponseDTO;
import org.fdryt.ornamental.dto.identification.ItemToListResponseDTO;
import org.fdryt.ornamental.service.PlantService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

@CrossOrigin(origins = "http://localhost:5173/", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/plants")
public class PlantController {

    private final PlantService plantService;

    // TODO: I should talk about count of products in number page
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAllOrnamentalPlants(@PageableDefault(size = 6) Pageable pageable) {
        return ResponseEntity.ok(plantService.findAllOrnamentalPlants(pageable));
    }

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
    }

    @GetMapping("identifications")
    public ResponseEntity<List<ItemToListResponseDTO>> findAllItemsToList(@PageableDefault(size = 30) Pageable pageable) {
        return ResponseEntity.ok(plantService.findAllItemsToList(pageable));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid CreatePlantDTO createPlantDTO) {
        return ResponseEntity.ok(plantService.create(createPlantDTO));
    }
}
