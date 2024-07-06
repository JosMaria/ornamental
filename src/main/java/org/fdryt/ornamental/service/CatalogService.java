package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.catalog.PlantCardResponseDTO;

import java.util.List;

public interface CatalogService {

    List<PlantCardResponseDTO> obtainPlantCards();
}
