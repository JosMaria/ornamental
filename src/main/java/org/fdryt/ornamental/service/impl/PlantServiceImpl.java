package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.Classification;
import org.fdryt.ornamental.domain.Plant;
import org.fdryt.ornamental.domain.TypeClassification;
import org.fdryt.ornamental.dto.CreatePlantDTO;
import org.fdryt.ornamental.dto.PlantResponseDTO;
import org.fdryt.ornamental.problem.exception.PlantAlreadyExistException;
import org.fdryt.ornamental.repository.ClassificationRepository;
import org.fdryt.ornamental.repository.PlantRepository;
import org.fdryt.ornamental.service.PlantService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;
    private final ClassificationRepository classificationRepository;
    private final ModelMapper plantMapper;

    @Override
    public PlantResponseDTO insert(CreatePlantDTO createPlantDTO) {
        if (plantRepository.existsByScientificName(createPlantDTO.getScientificName())) {
            String message = String.format("Plant with scientific name: %s already exists.", createPlantDTO.getScientificName());
            throw new PlantAlreadyExistException(message);
        } else {
            // Mapping the plant to be persisted.
            Plant plantToPersist = plantMapper.map(createPlantDTO, Plant.class);
            createPlantDTO.getClassifications().stream()
                    .map(this::findClassificationByType)
                    .forEach(plantToPersist::addClassification);

            // Saving the plant and print log
            Plant plantPersisted = plantRepository.save(plantToPersist);
            log.info("Inserted plant with a common name: {}", createPlantDTO.getCommonName());

            // Mapping plant to DTO
            return plantMapper.map(plantPersisted, PlantResponseDTO.class);
        }
    }

    private Classification findClassificationByType(TypeClassification type) {
        String msgException = String.format("Type classification %s does not exists", type);
        return classificationRepository.findClassificationByTypeClassification(type)
                .orElseThrow(() -> new IllegalArgumentException(msgException));
    }
}
