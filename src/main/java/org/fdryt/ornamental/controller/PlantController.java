package org.fdryt.ornamental.controller;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.CreatePlantDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/nursery")
public class PlantController {

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody CreatePlantDTO createPlantDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createPlantDTO.toString());
    }
}
