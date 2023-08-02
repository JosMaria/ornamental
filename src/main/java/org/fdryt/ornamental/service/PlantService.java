package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.plant.CreatePlantDTO;
import org.fdryt.ornamental.dto.plant.PlantResponseDTO;

public interface PlantService {

    PlantResponseDTO create(CreatePlantDTO payload);

    void delete(Integer id);
}
