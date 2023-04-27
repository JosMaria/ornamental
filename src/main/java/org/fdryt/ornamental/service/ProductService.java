package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.product.ItemToListResponseDTO;
import org.fdryt.ornamental.dto.product.ProductResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<ItemToListResponseDTO> findAllItemsToList(Pageable pageable);

    List<ProductResponseDTO> findAll(Pageable pageable);

    ProductResponseDTO findById(Integer id);
}
