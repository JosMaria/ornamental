package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.plant.CreatePlantDTO;
import org.fdryt.ornamental.dto.plant.PlantResponseDTO;

import java.util.List;

public interface PlantService {

    PlantResponseDTO create(CreatePlantDTO payload);

    void delete(Integer id);

    List<PlantResponseDTO> createAll(final List<CreatePlantDTO> plants);
}
