package org.fdryt.ornamental.service;

import org.fdryt.ornamental.domain.plant.Status;
import org.fdryt.ornamental.dto.nursery.ItemResponseDTO;
import org.fdryt.ornamental.dto.nursery.ProductResponseDTO;
import org.fdryt.ornamental.dto.nursery.SingleProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NurseryService {
    Page<ProductResponseDTO> findAllProducts(Pageable pageable);

    SingleProductResponseDTO findProductById(Integer id);

    Page<ItemResponseDTO> findAllItems(Pageable pageable);
}
