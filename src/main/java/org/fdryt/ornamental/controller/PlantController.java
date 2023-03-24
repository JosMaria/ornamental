package org.fdryt.ornamental.controller;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.ProductResponseDTO;
import org.fdryt.ornamental.dto.identification.ItemToListResponseDTO;
import org.fdryt.ornamental.service.PlantService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

@CrossOrigin(origins = "http://localhost:5173/", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/plants")
public class PlantController {

    private final PlantService plantService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAllOrnamentalPlants(
            @PageableDefault(size = 16, direction = ASC, sort = "identification.commonName") Pageable pageable) {
        return ResponseEntity.ok(plantService.findAllOrnamentalPlants(pageable));
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
}
