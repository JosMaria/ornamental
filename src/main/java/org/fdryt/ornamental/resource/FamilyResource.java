package org.fdryt.ornamental.resource;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.alternative.FamilyRequestDTO;
import org.fdryt.ornamental.dto.alternative.FamilyResponseDTO;
import org.fdryt.ornamental.service.FamilyServiceV2;
import org.fdryt.ornamental.utils.ValidList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/families")
public class FamilyResource {

    private final FamilyServiceV2 familyService;

    //TODO: add restrictions to @Valid
    @PostMapping
    public ResponseEntity<FamilyResponseDTO> saveFamily(@RequestBody @Valid FamilyRequestDTO payload) {
        return ResponseEntity
                .created(URI.create("/api/v2/families"))
                .body(familyService.createFamily(payload));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<FamilyResponseDTO>> saveFamilies(@RequestBody @Valid ValidList<FamilyRequestDTO> payload) {
        return ResponseEntity
                .created(URI.create("/api/v2/families/batch"))
                .body(familyService.createFamilies(payload));
    }
}
