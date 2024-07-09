package org.fdryt.ornamental.resource;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.repertory.ItemResponseDTO;
import org.fdryt.ornamental.service.RepertoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/repertory")
public class RepertoryResource {

    private final RepertoryService repertoryService;

    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> fetchAllItems() {
        return ResponseEntity.ok(repertoryService.obtainAllItems());
    }
}
