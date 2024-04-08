package org.fdryt.ornamental.service;

import org.fdryt.ornamental.domain.plant.alternative.enums.Classification;
import org.fdryt.ornamental.dto.nursery.ItemResponseDTO;
import org.fdryt.ornamental.dto.nursery.ProductResponseDTO;
import org.fdryt.ornamental.dto.nursery.SingleProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NurseryService {
    Page<ProductResponseDTO> findAllProducts(Pageable pageable);

    Page<ProductResponseDTO> findAllProductsByClassification(Pageable pageable, Classification classification);

    SingleProductResponseDTO findProductById(Integer id);

    Page<ItemResponseDTO> findAllItems(Pageable pageable);
}
