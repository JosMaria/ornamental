package org.fdryt.ornamental.controller;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.domain.plant.Classification;
import org.fdryt.ornamental.domain.plant.Status;
import org.fdryt.ornamental.dto.nursery.ItemResponseDTO;
import org.fdryt.ornamental.dto.nursery.ProductResponseDTO;
import org.fdryt.ornamental.dto.nursery.SingleProductResponseDTO;
import org.fdryt.ornamental.service.NurseryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/nursery")
public class NurseryController {

    private final NurseryService service;

    @GetMapping("/products")
    public ResponseEntity<Page<ProductResponseDTO>> fetchAllProducts(
//        @PageableDefault(size = 12) Pageable pageable,
//        @RequestParam(value = "classification", required = false) Classification classification,
//        @RequestParam(value = "status", required = false) Status status
    ) {
        return ResponseEntity.ok(service.findAllProducts());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<SingleProductResponseDTO> getProductById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.findProductById(id));
    }

    @GetMapping("/items")
    public ResponseEntity<Page<ItemResponseDTO>> getAllItems(
        @PageableDefault(size = 20) Pageable pageable,
        @RequestParam(value = "status", required = false) Status status
    ) {
        return ResponseEntity.ok(service.findAllItems(pageable, status));
    }
}
