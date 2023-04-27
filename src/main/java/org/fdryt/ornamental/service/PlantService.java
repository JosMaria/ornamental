package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.plant.CreatePlantDTO;
import org.fdryt.ornamental.dto.plant.PlantResponseDTO;
import org.fdryt.ornamental.dto.product.ItemToListResponseDTO;
import org.fdryt.ornamental.dto.product.ProductResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlantService {

    PlantResponseDTO create(CreatePlantDTO createPlantDTO);

    void delete(Integer id);

    /*List<ProductResponseDTO> findAllByClassification(String type, Pageable pageable);

    List<ProductResponseDTO> findAllOrnamentalPlantsByClassification(String type, Pageable pageable);

    List<ItemToListResponseDTO> findAllItemsToList(Pageable pageable);

    ProductResponseDTO findProductById(Integer id);*/
}
