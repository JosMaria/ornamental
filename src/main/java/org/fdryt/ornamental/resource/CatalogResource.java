package org.fdryt.ornamental.resource;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.domain.plant.enums.Classification;
import org.fdryt.ornamental.dto.catalog.PlantCardResponseDTO;
import org.fdryt.ornamental.service.CatalogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/catalog")
public class CatalogResource {

    private final CatalogService catalogService;

    @GetMapping
    public ResponseEntity<Page<PlantCardResponseDTO>> fetchPlantCards(
            @PageableDefault(size = 12) Pageable pageable,
            @RequestParam(required = false) Classification classification
    ) {
        return ResponseEntity.ok(catalogService.obtainPlantCards(pageable, classification));
    }
}
