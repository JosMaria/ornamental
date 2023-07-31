package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.MyCreatePlantDTO;
import org.fdryt.ornamental.dto.MyPlantResponseDTO;
import org.fdryt.ornamental.dto.plant.CreatePlantDTO;
import org.fdryt.ornamental.dto.plant.PlantResponseDTO;

import java.util.List;

public interface PlantService {

    MyPlantResponseDTO create(MyCreatePlantDTO payload);
//    PlantResponseDTO create(CreatePlantDTO createPlantDTO);

    void delete(Integer id);

    List<PlantResponseDTO> createAll(List<CreatePlantDTO> list);
}
