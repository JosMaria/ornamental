package org.fdryt.ornamental.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.plant.*;
import org.fdryt.ornamental.dto.nursery.SingleProductResponseDTO;
import org.fdryt.ornamental.dto.plant.TechnicalSheetDTO;
import org.fdryt.ornamental.repository.PlantJpaRepository;
import org.fdryt.ornamental.service.NurseryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class NurseryServiceImpl implements NurseryService {

    private final PlantJpaRepository plantJpaRepository;

    @Override
    public SingleProductResponseDTO findProductById(final Integer id) {
        Plant plantObtained = plantJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Planta con ID: %s no fue encontrada".formatted(id)));
        log.info("Plant with ID: {} fetched", plantObtained.getId());
        return toSingleProductResponseDTO(plantObtained);
    }

    private SingleProductResponseDTO toSingleProductResponseDTO(Plant plant) {
        FundamentalData fundamentalData = plant.getFundamentalData();
        ScientificName scientificName = fundamentalData.getScientificName();
        Family familyObtained = fundamentalData.getFamily();

        return new SingleProductResponseDTO(
                plant.getId(),
                fundamentalData.getCommonName(),
                scientificName.getName(),
                scientificName.getScientistLastnameInitial(),
                familyObtained != null ? familyObtained.getName() : null,
                plant.getStatus(),
                fundamentalData.getClassifications(),
                plant.getDescription(),
                Set.of("url1", "url2", "url3"),
                plant.getDetails().stream()
                        .map(Detail::getDetail)
                        .collect(Collectors.toCollection(ArrayList::new)),
                plant.getTechnicalSheets().stream()
                        .map(item -> new TechnicalSheetDTO(item.getWord(), item.getInfo()))
                        .collect(Collectors.toCollection(ArrayList::new)),
                plant.getPrice()
        );
    }
}
