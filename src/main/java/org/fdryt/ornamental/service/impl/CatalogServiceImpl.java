package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.plant.alternative.PlantV3;
import org.fdryt.ornamental.dto.catalog.PlantCardDTO;
import org.fdryt.ornamental.dto.catalog.PlantCardResponseDTO;
import org.fdryt.ornamental.repository.PlantJpaRepositoryV2;
import org.fdryt.ornamental.service.CatalogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public List<PlantCardResponseDTO> obtainPlantCards(Pageable pageable) {
        List<PlantCardDTO> plantCardsObtained = plantJpaRepository.findAllPlantCards();
        log.info("Fetch all plant cards");

        return plantCardsObtained.stream()
                .map(this::toPlantCardResponseDTO)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private PlantCardResponseDTO toPlantCardResponseDTO(PlantCardDTO plantCardDTO) {
        String scientificName = buildScientificName(plantCardDTO.scientificName(), plantCardDTO.discoverer());
        return new PlantCardResponseDTO(
                plantCardDTO.id(),
                firstLetterToCapitalize(plantCardDTO.commonName()),
                scientificName,
                firstLetterToCapitalize(plantCardDTO.familyName()),
                plantCardDTO.status(),
                "https://img.freepik.com/foto-gratis/hermosas-modernas-plantas-deco_23-2149198578.jpg?size=626&ext=jpg"
        );
    }
}
