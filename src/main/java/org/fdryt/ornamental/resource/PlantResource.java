package org.fdryt.ornamental.resource;

import jakarta.validation.Valid;
import org.fdryt.ornamental.dto.alternative.PlantRequestDTO;
import org.fdryt.ornamental.dto.alternative.PlantResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v2/plants")
public class PlantResource {

    @PostMapping
    public ResponseEntity<PlantResponseDTO> create(@Valid @RequestBody PlantRequestDTO plant) {
        return null;
    }
}
