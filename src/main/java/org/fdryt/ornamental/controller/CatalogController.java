package org.fdryt.ornamental.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/nursery/catalog")
public class CatalogController {

    @GetMapping
    public ResponseEntity<String> showCatalog() {
        return ResponseEntity.ok("Here will be the Catalog");
    }
}
