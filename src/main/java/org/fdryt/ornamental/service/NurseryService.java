package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.nursery.SingleProductResponseDTO;

public interface NurseryService {

    SingleProductResponseDTO findProductById(Integer id);
}
