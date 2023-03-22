package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.ProductResponseDTO;
import org.fdryt.ornamental.dto.identification.IdentificationResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlantService {

    List<ProductResponseDTO> findAllOrnamentalPlants(Pageable pageable);

    List<ProductResponseDTO> findAllOrnamentalPlantsByClassification(String type, Pageable pageable);

    IdentificationResponseDTO test();
}
