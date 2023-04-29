package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.product.ItemToListResponseDTO;
import org.fdryt.ornamental.dto.product.ProductResponseDTO;
import org.fdryt.ornamental.dto.product.SingleProductResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<ItemToListResponseDTO> findAllItemsToList(Pageable pageable);

    List<ItemToListResponseDTO> findAllItemsToListByStatus(String status, Pageable pageable);

    List<ProductResponseDTO> findAll(Pageable pageable);

    SingleProductResponseDTO findById(Integer id);

    List<ProductResponseDTO> findAllByClassification(String classification, Pageable pageable);
}
