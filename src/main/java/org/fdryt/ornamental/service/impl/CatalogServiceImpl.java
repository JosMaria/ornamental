package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.plant.enums.Classification;
import org.fdryt.ornamental.dto.catalog.PlantCardDTO;
import org.fdryt.ornamental.dto.catalog.PlantCardResponseDTO;
import org.fdryt.ornamental.dto.repertory.ItemDTO;
import org.fdryt.ornamental.dto.repertory.ItemResponseDTO;
import org.fdryt.ornamental.repository.PlantJpaRepository;
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

    private final PlantJpaRepository plantJpaRepository;

    @Override
    public Page<PlantCardResponseDTO> obtainPlantCards(Pageable pageable, Classification classification) {
        int limit = pageable.getPageSize();
        int offset = pageable.getPageNumber() * limit;

        if (classification == null) {
            log.info("Obtained all the plants on page number: {}", pageable.getPageNumber());
            return obtainPlantCardsMapped(
                    pageable,
                    plantJpaRepository.count(),
                    plantJpaRepository.findAllPlantCards(limit, offset)
            );
        } else {
            log.info("Obtained all the plants on page number: {} and with classification: {}", pageable.getPageNumber(), classification);
            return obtainPlantCardsMapped(
                    pageable,
                    plantJpaRepository.countByClassification(classification.name()),
                    plantJpaRepository.findPlantCardsByClassification(limit, offset, classification.name())
            );
        }
    }

    @Override
    public List<ItemResponseDTO> obtainAllItems() {
        List<ItemDTO> itemsObtained = plantJpaRepository.findAllItems();
        log.info("Obtained all plants to show in the repertory");

        return itemsObtained.stream()
                .map(this::toItemResponseDTO)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Page<PlantCardResponseDTO> obtainPlantCardsMapped(Pageable pageable, long count, List<PlantCardDTO> plantCardsObtained) {
        List<PlantCardResponseDTO> plantsMapped = plantCardsObtained.stream()
                .map(this::toPlantCardResponseDTO)
                .collect(Collectors.toCollection(ArrayList::new));

        return new PageImpl<>(plantsMapped, pageable, count);
    }

    private ItemResponseDTO toItemResponseDTO(ItemDTO itemDTO) {
        String scientificName = buildScientificName(itemDTO.scientificName(), itemDTO.discoverer());
        String commonName = firstLetterToCapitalize(itemDTO.commonName());
        String familyName = firstLetterToCapitalize(itemDTO.familyName());
        return new ItemResponseDTO(commonName, scientificName, familyName);
    }

    private PlantCardResponseDTO toPlantCardResponseDTO(PlantCardDTO plantCardDTO) {
        String scientificName = buildScientificName(plantCardDTO.scientificName(), plantCardDTO.discoverer());
        return new PlantCardResponseDTO(
                plantCardDTO.id(),
                firstLetterToCapitalize(plantCardDTO.commonName()),
                scientificName,
                plantCardDTO.status(),
                "https://img.freepik.com/foto-gratis/hermosas-modernas-plantas-deco_23-2149198578.jpg?size=626&ext=jpg"
        );
    }
}
