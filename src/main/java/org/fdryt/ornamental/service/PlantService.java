package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.CreatePlantDTO;
import org.fdryt.ornamental.dto.PlantResponseDTO;

public interface PlantService {

    PlantResponseDTO insert(CreatePlantDTO createPlantDTO);
}
