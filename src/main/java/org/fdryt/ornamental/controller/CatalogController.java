package org.fdryt.ornamental.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/nursery/catalog")
public class CatalogController {

    @GetMapping
    public ResponseEntity<String> showCatalog() {
        return ResponseEntity.ok("Accessibility: PUBLIC\nHere will be the catalog");
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

    // TODO:
    @GetMapping("permitTen")
    @PreAuthorize("hasAuthority('permission:ten') or hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<String> messageAccessibilityToPermissionTenAndAdministrator() {
        String message = "Accessibility: permission:ten or ADMINISTRATOR\nThis resource is only to permission:ten or ROL ADMINISTRATOR";
        return ResponseEntity.ok(message);
    }
}
