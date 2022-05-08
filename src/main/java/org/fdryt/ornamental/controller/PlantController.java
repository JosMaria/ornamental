package org.fdryt.ornamental.controller;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.CreatePlantDTO;
import org.fdryt.ornamental.dto.Family;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/nursery")
public class PlantController {

    @PostMapping
    public ResponseEntity<CreatePlantDTO> insert(@Valid @RequestBody CreatePlantDTO createPlantDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createPlantDTO);
    }
}
