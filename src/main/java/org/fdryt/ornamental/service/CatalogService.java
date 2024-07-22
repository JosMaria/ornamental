package org.fdryt.ornamental.service;

import org.fdryt.ornamental.domain.plant.enums.Classification;
import org.fdryt.ornamental.dto.catalog.PlantCardResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CatalogService {

    Page<PlantCardResponseDTO> obtainPlantCards(Pageable pageable, Classification classification);
}
