package org.fdryt.ornamental.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.plant.Detail;
import org.fdryt.ornamental.domain.plant.Note;
import org.fdryt.ornamental.domain.plant.Plant;
import org.fdryt.ornamental.dto.nursery.ItemResponseDTO;
import org.fdryt.ornamental.dto.nursery.ProductResponseDTO;
import org.fdryt.ornamental.dto.nursery.SingleProductResponseDTO;
import org.fdryt.ornamental.dto.plant.TechnicalSheetDTO;
import org.fdryt.ornamental.repository.PlantJpaRepository;
import org.fdryt.ornamental.service.NurseryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class NurseryServiceImpl implements NurseryService {

    private final PlantJpaRepository plantJpaRepository;

    @Override
    public Page<ProductResponseDTO> findAllProducts(Pageable pageable) {
        Page<ProductResponseDTO> productsPageable = plantJpaRepository.findAllProducts(pageable);
        log.info("Products fo the number {} fetched.", productsPageable.getNumber());
        return productsPageable;
    }

    @Override
    public SingleProductResponseDTO findProductById(final Integer id) {
        Plant plantObtained = plantJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Planta con ID: %s no fue encontrada".formatted(id)));
        log.info("Plant with ID: {} fetched", plantObtained.getId());

        return new SingleProductResponseDTO(
                plantObtained.getId(),
                plantObtained.getFundamentalData().getCommonName(),
                plantObtained.getFundamentalData().getScientificName().getName(),
                plantObtained.getFundamentalData().getScientificName().getScientistLastnameInitial(),
                plantObtained.getFundamentalData().getFamily().getName(),
                plantObtained.getStatus(),
                plantObtained.getFundamentalData().getClassifications(),
                plantObtained.getDescription(),
                Set.of("url1", "url2", "url3"),
                plantObtained.getNotes().stream()
                        .map(Note::getNote)
                        .collect(Collectors.toCollection(ArrayList::new)),
                plantObtained.getDetails().stream()
                        .map(Detail::getDetail)
                        .collect(Collectors.toCollection(ArrayList::new)),
                plantObtained.getTechnicalSheets().stream()
                        .map(item -> new TechnicalSheetDTO(item.getWord(), item.getInfo()))
                        .collect(Collectors.toCollection(ArrayList::new))
        );
    }

    @Override
    public Page<ItemResponseDTO> findAllItems(Pageable pageable) {
        Page<ItemResponseDTO> itemsPageable = plantJpaRepository.findAllItems(pageable);
        log.info("Items of the number page: {} fetched", itemsPageable.getNumber());
        return itemsPageable;
    }
}
