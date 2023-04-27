package org.fdryt.ornamental.controller;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.product.ItemToListResponseDTO;
import org.fdryt.ornamental.dto.product.ProductResponseDTO;
import org.fdryt.ornamental.service.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("identifications")
    public ResponseEntity<List<ItemToListResponseDTO>> findAllItemsToList(@PageableDefault(size = 30) Pageable pageable) {
        return ResponseEntity.ok(productService.findAllItemsToList(pageable));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll(@PageableDefault(size = 6) Pageable pageable) {
        return ResponseEntity.ok(productService.findAll(pageable));
    }
}
