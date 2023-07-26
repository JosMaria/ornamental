package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.product.ItemToListResponseDTO;
import org.fdryt.ornamental.dto.product.ProductResponseDTO;
import org.fdryt.ornamental.dto.product.SingleProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Page<ItemToListResponseDTO> findAllItemsToList(Pageable pageable);

    Page<ItemToListResponseDTO> findAllItemsToListByStatus(String status, Pageable pageable);

    Page<ProductResponseDTO> findAll(Pageable pageable);

    SingleProductResponseDTO findById(Integer id);

    Page<ProductResponseDTO> findAllByClassification(String classification, Pageable pageable);
}
