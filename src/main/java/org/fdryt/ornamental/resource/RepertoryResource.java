package org.fdryt.ornamental.resource;


import org.fdryt.ornamental.dto.repertory.ItemResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v2/repertory")
public class RepertoryResource {

    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> fetchAllItems() {
        ItemResponseDTO itemOne = new ItemResponseDTO("Calatea zebrina", "Calathea zebrina", "Marantaceae");
        ItemResponseDTO itemTwo = new ItemResponseDTO("Calatea zebrina", null, null);
        ItemResponseDTO itemThree = new ItemResponseDTO("Calatea zebrina", null, "Marantaceae");

        return ResponseEntity.ok(List.of(
                itemOne, itemTwo, itemThree
        ));
    }
}
