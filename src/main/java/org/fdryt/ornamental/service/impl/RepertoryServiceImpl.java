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

import static org.fdryt.ornamental.utils.Converters.buildScientificName;
import static org.fdryt.ornamental.utils.Converters.firstLetterToCapitalize;

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
                .map(this::toItemResponseDTO)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private ItemResponseDTO toItemResponseDTO(PlantV3 plant) {
        String scientificName = buildScientificName(plant.getScientificName(), plant.getDiscoverer());
        return new ItemResponseDTO(
                firstLetterToCapitalize(plant.getCommonName()),
                scientificName,
                firstLetterToCapitalize(plant.getFamily().getName())
        );
    }
}
