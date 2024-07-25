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

        long count;
        List<PlantCardDTO> plantCardsObtained;
        if (classification == null) {
            count = plantJpaRepository.count();
            plantCardsObtained = plantJpaRepository.findAllPlantCards(limit, offset);

        } else {
            count = plantJpaRepository.countByClassification(classification.name());
            plantCardsObtained = plantJpaRepository.findPlantCardsByClassification(limit, offset, classification.name());
        }

        List<PlantCardResponseDTO> plantCardsConverted = plantCardsObtained.stream()
                .map(this::toPlantCardResponseDTO)
                .collect(Collectors.toCollection(ArrayList::new));
        log.info("Fetch all plant of the number page: {}", pageable.getPageNumber());

        return new PageImpl<>(plantCardsConverted, pageable, count);
    }
    
    @Override
    public List<ItemResponseDTO> obtainAllItems() {
        List<ItemDTO> itemsObtained = plantJpaRepository.findAllItems();
        log.info("Obtained all plants to show in the repertory");

        return itemsObtained.stream()
                .map(this::toItemResponseDTO)
                .collect(Collectors.toCollection(ArrayList::new));
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
                firstLetterToCapitalize(plantCardDTO.familyName()),
                plantCardDTO.status(),
                "https://img.freepik.com/foto-gratis/hermosas-modernas-plantas-deco_23-2149198578.jpg?size=626&ext=jpg"
        );
    }
}
