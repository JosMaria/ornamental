package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.CreatePlantDTO;
import org.fdryt.ornamental.dto.ProductResponseDTO;
import org.fdryt.ornamental.dto.identification.ItemToListResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlantService {

    List<ProductResponseDTO> findAllOrnamentalPlants(Pageable pageable);

    List<ProductResponseDTO> findAllByClassification(String type, Pageable pageable);

    List<ProductResponseDTO> findAllOrnamentalPlantsByClassification(String type, Pageable pageable);

    List<ItemToListResponseDTO> findAllItemsToList(Pageable pageable);

    ProductResponseDTO findProductById(Integer id);

    ProductResponseDTO create(CreatePlantDTO createPlantDTO);
}