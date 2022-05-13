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
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;
    private final ClassificationRepository classificationRepository;

    @Override
    public PlantResponseDTO insert(CreatePlantDTO createPlantDTO) {
        if (plantRepository.existsByScientificName(createPlantDTO.getScientificName())) {
            String message = String.format("Plant with scientific name: %s already exists.", createPlantDTO.getScientificName());
            throw new PlantAlreadyExistException(message);
        } else {
            Plant plantToPersist = dtoToEntity(createPlantDTO);
            Plant plantPersisted = plantRepository.save(plantToPersist);
            log.info("Inserted plant with a common name: {}", createPlantDTO.getCommonName());
            return entityToDTO(plantPersisted);
        }
    }

    private Plant dtoToEntity(CreatePlantDTO dto) {
        Plant plant = new Plant();
        plant.setCommonName(dto.getCommonName());
        plant.setScientificName(dto.getScientificName());
        plant.setFamily(dto.getFamily());

        dto.getClassifications().stream()
                .map(this::findClassification)
                .forEach(plant::addClassification);

        return plant;
    }

    private Classification findClassification(TypeClassification type) {
        String msgException = String.format("Type classification %s does not exists", type);
        return classificationRepository.findClassificationByTypeClassification(type)
                .orElseThrow(() -> new IllegalArgumentException(msgException));
    }

    private PlantResponseDTO entityToDTO(Plant plant) {
        PlantResponseDTO dto = new PlantResponseDTO();
        dto.setCommonName(plant.getCommonName());
        dto.setScientificName(plant.getScientificName());
        dto.setFamily(plant.getFamily());
        Set<TypeClassification> typeClassifications = plant.getClassifications()
                .stream()
                .map(Classification::getTypeClassification)
                .collect(Collectors.toSet());
        dto.setClassifications(typeClassifications);
        return dto;
    }
}
