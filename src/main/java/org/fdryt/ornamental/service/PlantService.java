package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.plant.CreatePlantDTO;
import org.fdryt.ornamental.dto.plant.PlantResponseDTO;

public interface PlantService {

    PlantResponseDTO create(CreatePlantDTO createPlantDTO);

    void delete(Integer id);
}
