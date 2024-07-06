package org.fdryt.ornamental.resource;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.catalog.PlantCardResponseDTO;
import org.fdryt.ornamental.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/catalog")
public class CatalogResource {

    private final CatalogService catalogService;

    @GetMapping
    public ResponseEntity<List<PlantCardResponseDTO>> fetch() {
        return ResponseEntity.ok(catalogService.obtainPlantCards());
    }
}
