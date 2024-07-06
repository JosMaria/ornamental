package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.plant.alternative.PlantV3;
import org.fdryt.ornamental.dto.catalog.PlantCardResponseDTO;
import org.fdryt.ornamental.repository.PlantJpaRepositoryV2;
import org.fdryt.ornamental.service.CatalogService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.fdryt.ornamental.utils.Converters.buildScientificName;
import static org.fdryt.ornamental.utils.Converters.firstLetterToCapitalize;

@Service
@Slf4j
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final PlantJpaRepositoryV2 plantJpaRepository;

    @Override
    public List<PlantCardResponseDTO> obtainPlantCards() {
        List<PlantV3> plantsObtained = plantJpaRepository.findAll();
        log.info("Fetch all plant cards");

        return plantsObtained.stream()
                .map(this::toPlantCardResponseDTO)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private PlantCardResponseDTO toPlantCardResponseDTO(PlantV3 plant) {
        String scientificName = buildScientificName(plant.getScientificName(), plant.getDiscoverer());

        return new PlantCardResponseDTO(
                plant.getId(),
                firstLetterToCapitalize(plant.getCommonName()),
                scientificName,
                firstLetterToCapitalize(plant.getFamily().getName()),
                plant.getStatus(),
                "https://img.freepik.com/foto-gratis/hermosas-modernas-plantas-deco_23-2149198578.jpg?size=626&ext=jpg"
        );
    }
}
