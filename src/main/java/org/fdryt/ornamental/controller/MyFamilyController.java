package org.fdryt.ornamental.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.family.CreateFamilyDTO;
import org.fdryt.ornamental.dto.family.FamilyResponseDTO;
import org.fdryt.ornamental.service.MyFamilyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/my_families")
public class MyFamilyController {

    private final MyFamilyService familyService;

    @PostMapping
    public ResponseEntity<FamilyResponseDTO> create(@RequestBody @Valid CreateFamilyDTO payload) {
        return new ResponseEntity<>(familyService.create(payload), CREATED);
    }
}
