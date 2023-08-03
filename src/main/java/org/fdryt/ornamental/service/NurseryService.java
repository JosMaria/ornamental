package org.fdryt.ornamental.service;

import org.fdryt.ornamental.domain.plant.Classification;
import org.fdryt.ornamental.domain.plant.Status;
import org.fdryt.ornamental.dto.product.ItemResponseDTO;
import org.fdryt.ornamental.dto.product.ProductResponseDTO;
import org.fdryt.ornamental.dto.product.SingleProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NurseryService {
    Page<ProductResponseDTO> findAllProducts(Pageable pageable, Classification classification, Status status);

    SingleProductResponseDTO findProductById(Integer id);

    Page<ItemResponseDTO> findAllItems(Pageable pageable, Classification classification, Status status);
}
