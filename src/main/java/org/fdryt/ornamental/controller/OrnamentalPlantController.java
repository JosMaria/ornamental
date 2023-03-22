package org.fdryt.ornamental.controller;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.ProductResponseDTO;
import org.fdryt.ornamental.service.OrnamentalPlantService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

@CrossOrigin(origins = "http://localhost:5173/", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/ornamental_plants")
public class OrnamentalPlantController {

    private final OrnamentalPlantService ornamentalPlantService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAllOrnamentalPlants(
            @PageableDefault(size = 16, direction = ASC, sort = "identification.commonName") Pageable pageable) {
        return ResponseEntity.ok(ornamentalPlantService.findAllOrnamentalPlants(pageable));
    }

    @GetMapping("classifications/{classification}")
    public ResponseEntity<List<ProductResponseDTO>> findAllOrnamentalPlantsByClassification(
            @PathVariable("classification") String classification,
            @PageableDefault(size = 16, direction = ASC, sort = "identification.commonName") Pageable pageable) {
        return ResponseEntity.ok(ornamentalPlantService.findAllOrnamentalPlantsByClassification(classification, pageable));
    }

    @GetMapping("administrator")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<String> messageAccessibilityAdministrator() {
        String message = "Accessibility: ADMIN\nThis resource is only to ADMINISTRATOR";
        return ResponseEntity.ok(message);
    }

    @GetMapping("assistantAdministrator")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_ASSISTANT')")
    public ResponseEntity<String> messageAccessibilityAdministratorAndAssistant() {
        String message = "Accessibility: ADMINISTRATOR & ASSISTANT\nThis resource is only to ADMINISTRATOR & ASSISTANT";
        return ResponseEntity.ok(message);
    }

    @GetMapping("assistant")
    @PreAuthorize("hasRole('ROLE_ASSISTANT')")
    public ResponseEntity<String> messageAccessibilityAssistant() {
        String message = "Accessibility: ASSISTANT\nThis resource is only to ASSISTANT";
        return ResponseEntity.ok(message);
    }

    @GetMapping("permitOne")
    @PreAuthorize("hasAuthority('permission:one')")
    public ResponseEntity<String> messageAccessibilityToPermitOne() {
        String message = "Accessibility: permission:one\nThis resource is only to permission:one";
        return ResponseEntity.ok(message);
    }

    @GetMapping("permitSeven")
    @PreAuthorize("hasAuthority('permission:seven')")
    public ResponseEntity<String> messageAccessibilityToPermissionSeven() {
        String message = "Accessibility: permission:seven\nThis resource is only to permission:seven";
        return ResponseEntity.ok(message);
    }

    @GetMapping("permitEight")
    @PreAuthorize("hasAuthority('permission:eight')")
    public ResponseEntity<String> messageAccessibilityToPermitEight() {
        String message = "Accessibility: permission:eight\nThis resource is only to permission:eight";
        return ResponseEntity.ok(message);
    }

    @GetMapping("permitNine")
    @PreAuthorize("hasAuthority('permission:nine')")
    public ResponseEntity<String> messageAccessibilityToPermissionNine() {
        String message = "Accessibility: permission:nine\nThis resource is only to permission:nine";
        return ResponseEntity.ok(message);
    }

    @GetMapping("permitTen")
    @PreAuthorize("hasAuthority('permission:ten') or hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<String> messageAccessibilityToPermissionTenAndAdministrator() {
        String message = "Accessibility: permission:ten or ADMINISTRATOR\nThis resource is only to permission:ten or ROL ADMINISTRATOR";
        return ResponseEntity.ok(message);
    }
}
