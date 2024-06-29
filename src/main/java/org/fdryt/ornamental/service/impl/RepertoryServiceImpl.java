package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.plant.alternative.PlantV3;
import org.fdryt.ornamental.dto.repertory.ItemResponseDTO;
import org.fdryt.ornamental.repository.RepertoryRepository;
import org.fdryt.ornamental.service.RepertoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RepertoryServiceImpl implements RepertoryService {

    private final RepertoryRepository repertoryRepository;

    @Override
    public List<ItemResponseDTO> obtainAllItems() {
        List<PlantV3> plantsObtained = repertoryRepository.findAll();
        log.info("Obtained all plants to show in the repertory");

        return plantsObtained.stream()
                .map(plant -> {
                    String scientificName = null;
                    if (plant.getScientificName() != null && !plant.getScientificName().isEmpty()) {
                        scientificName = plant.getScientificName().substring(0, 1).toUpperCase() + plant.getScientificName().substring(1);
                        if (plant.getDiscoverer() != null) {
                            scientificName += " " + Character.toUpperCase(plant.getDiscoverer());
                        }
                    }
                    return new ItemResponseDTO(plant.getCommonName(), scientificName, plant.getFamily().getName());
                    })
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
