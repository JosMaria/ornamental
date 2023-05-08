package org.fdryt.ornamental.controller;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.product.ItemToListResponseDTO;
import org.fdryt.ornamental.dto.product.ProductResponseDTO;
import org.fdryt.ornamental.dto.product.SingleProductResponseDTO;
import org.fdryt.ornamental.service.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/identifications")
    public ResponseEntity<List<ItemToListResponseDTO>> findAllItemsToList(@PageableDefault(size = 30) Pageable pageable) {
        return ResponseEntity.ok(productService.findAllItemsToList(pageable));
    }

    @GetMapping("/identifications/status/{status}")
        public ResponseEntity<List<ItemToListResponseDTO>> findAllItemsToListByStatus(
            @PathVariable("status") String status, @PageableDefault(size = 30) Pageable pageable) {
        return ResponseEntity.ok(productService.findAllItemsToListByStatus(status, pageable));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll(@PageableDefault(size = 12) Pageable pageable) {
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SingleProductResponseDTO> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/classifications/{classification}")
    public ResponseEntity<List<ProductResponseDTO>> findAllByClassification(
            @PathVariable("classification") String classification, @PageableDefault(size = 30) Pageable pageable) {
        return ResponseEntity.ok(productService.findAllByClassification(classification, pageable));
    }
}
