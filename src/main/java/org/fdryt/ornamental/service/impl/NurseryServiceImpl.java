package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.plant.Classification;
import org.fdryt.ornamental.domain.plant.Status;
import org.fdryt.ornamental.dto.nursery.ItemResponseDTO;
import org.fdryt.ornamental.dto.nursery.ProductResponseDTO;
import org.fdryt.ornamental.dto.nursery.SingleProductResponseDTO;
import org.fdryt.ornamental.repository.PlantRepository;
import org.fdryt.ornamental.service.NurseryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class NurseryServiceImpl implements NurseryService {

    private final PlantRepository plantRepository;

    @Override
    public Page<ProductResponseDTO> findAllProducts() {

        return null;
    }

    @Override
    public SingleProductResponseDTO findProductById(Integer id) {
        return null;
    }

    @Override
    public Page<ItemResponseDTO> findAllItems(Pageable pageable, Status status) {
        /*
        * SELECT
        *   id,
        *   fundamentalData.commonName,
        *   fundamentalData.scientificName,
        *   fundamentalData.scientistLastnameInitial,
        *   status,
        *   familyName
        *
        * FROM Plant p
        * INNER JOIN Family f
        * WHERE p.status = :status
        * */
        return null;
    }
}
