package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.ProductResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrnamentalPlantService {

    List<ProductResponseDTO> findAllOrnamentalPlants(Pageable pageable);

    List<ProductResponseDTO> findAllOrnamentalPlantsByClassification(String type, Pageable pageable);
}
