package org.fdryt.ornamental.controller;
    /*
    import lombok.RequiredArgsConstructor;
    import org.fdryt.ornamental.dto.CreatePlantDTO;
    import org.fdryt.ornamental.dto.PlantResponseDTO;
    import org.fdryt.ornamental.service.PlantService;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.web.bind.annotation.*;

    import javax.validation.Valid;

    @RestController
    @RequiredArgsConstructor
    @RequestMapping("api/v1/nursery")
    public class PlantController {

        private final PlantService plantService;

        @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
        @PostMapping
        public ResponseEntity<PlantResponseDTO> insert(@Valid @RequestBody CreatePlantDTO createPlantDTO) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(plantService.insert(createPlantDTO));
        }

        @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
        @PostMapping("family")
        public ResponseEntity<String> insertFamily(@RequestParam("family") String family) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Created " + "'" + family + "'");
        }
    }*/
