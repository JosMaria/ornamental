package org.fdryt.ornamental.controller;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.nursery.SingleProductResponseDTO;
import org.fdryt.ornamental.service.NurseryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:5173/", "https://magnificent-daifuku-409cab.netlify.app/"}, allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/nursery")
public class NurseryController {

    private final NurseryService service;

    @GetMapping("/product/{id}")
    public ResponseEntity<SingleProductResponseDTO> fetchProductById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.findProductById(id));
    }
}
