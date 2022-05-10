package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.dto.CreatePlantDTO;
import org.fdryt.ornamental.dto.PlantResponseDTO;
import org.fdryt.ornamental.problem.exception.PlantAlreadyExistException;
import org.fdryt.ornamental.repository.PlantRepository;
import org.fdryt.ornamental.service.PlantService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;

    @Override
    public PlantResponseDTO insert(CreatePlantDTO createPlantDTO) {
        if (plantRepository.existsByScientificName(createPlantDTO.getScientificName())) {
            String message = String.format("Plant with scientific name: %s already exists.", createPlantDTO.getScientificName());
            throw new PlantAlreadyExistException(message);
        } else {
            log.info("inserted plant with common name: {}", createPlantDTO.getCommonName());
            return null;
        }
    }
}
