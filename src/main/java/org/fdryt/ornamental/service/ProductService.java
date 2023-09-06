package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.nursery.ItemToListResponseDTO;
import org.fdryt.ornamental.dto.nursery.ProductResponseDTO;
import org.fdryt.ornamental.dto.nursery.SingleProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ItemToListResponseDTO> findAllItemsToList(Pageable pageable);

    Page<ItemToListResponseDTO> findAllItemsToListByStatus(String status, Pageable pageable);

    Page<ProductResponseDTO> findAll(Pageable pageable);

    SingleProductResponseDTO findById(Integer id);

    Page<ProductResponseDTO> findAllByClassification(String classification, Pageable pageable);
}
