package org.fdryt.ornamental.controller;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.domain.plant.Classification;
import org.fdryt.ornamental.dto.nursery.ItemResponseDTO;
import org.fdryt.ornamental.dto.nursery.ProductResponseDTO;
import org.fdryt.ornamental.dto.nursery.SingleProductResponseDTO;
import org.fdryt.ornamental.service.NurseryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:5173/", "https://magnificent-daifuku-409cab.netlify.app/"}, allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/nursery")
public class NurseryController {

    private final NurseryService service;

    @GetMapping("/product")
    public ResponseEntity<Page<ProductResponseDTO>> fetchAllProducts(@PageableDefault(size = 12) Pageable pageable) {
        return ResponseEntity.ok(service.findAllProducts(pageable));
    }

    @GetMapping("/product/classification/{classification}")
    public ResponseEntity<Page<ProductResponseDTO>> fetchAllProducts(
            @PageableDefault(size = 12) Pageable pageable,
            @PathVariable("classification") Classification classification
    ) {
        return ResponseEntity.ok(service.findAllProductsByClassification(pageable, classification));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<SingleProductResponseDTO> fetchProductById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.findProductById(id));
    }

    @GetMapping("/item")
    public ResponseEntity<Page<ItemResponseDTO>> fetchAllItems(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(service.findAllItems(pageable));
    }
}
