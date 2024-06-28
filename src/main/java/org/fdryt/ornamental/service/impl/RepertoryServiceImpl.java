package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.repertory.ItemResponseDTO;
import org.fdryt.ornamental.repository.RepertoryRepository;
import org.fdryt.ornamental.service.RepertoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepertoryServiceImpl implements RepertoryService {

    private final RepertoryRepository repertoryRepository;

    @Override
    public List<ItemResponseDTO> obtainAllItems() {
        ItemResponseDTO itemOne = new ItemResponseDTO("Calatea zebrina", "Calathea zebrina", "Marantaceae");
        ItemResponseDTO itemTwo = new ItemResponseDTO("Calatea zebrina", null, null);
        ItemResponseDTO itemThree = new ItemResponseDTO("Calatea zebrina", null, "Marantaceae");
        return null;
    }
}
