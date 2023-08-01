package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.plant.CreatePlantDTO;
import org.fdryt.ornamental.dto.MyPlantResponseDTO;

public interface PlantService {

    MyPlantResponseDTO create(CreatePlantDTO payload);

    void delete(Integer id);
}
