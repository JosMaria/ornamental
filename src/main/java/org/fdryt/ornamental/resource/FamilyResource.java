package org.fdryt.ornamental.resource;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.alternative.FamilyRequestDTO;
import org.fdryt.ornamental.dto.alternative.FamilyResponseDTO;
import org.fdryt.ornamental.service.FamilyServiceV2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/families")
public class FamilyResource {

    private final FamilyServiceV2 familyService;

    @PostMapping
    public ResponseEntity<FamilyResponseDTO> saveFamily(@RequestBody FamilyRequestDTO payload) {
        return ResponseEntity
                .created(URI.create("/families/familyID"))
                .body(familyService.createFamily(payload));
    }
}
