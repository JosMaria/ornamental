package org.fdryt.ornamental.controller;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.service.ClassificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/classifications")
public class ClassificationController {

    private final ClassificationService classificationService;

    @GetMapping
    public ResponseEntity<Set<String>> findAllClassificationByUtility() {
        return ResponseEntity.ok(classificationService.findAllClassificationByUtility());
    }
}
