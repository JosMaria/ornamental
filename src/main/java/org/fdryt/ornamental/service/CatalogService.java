package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.catalog.PlantCardResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CatalogService {

    List<PlantCardResponseDTO> obtainPlantCards(Pageable pageable);
}
