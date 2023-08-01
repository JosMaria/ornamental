package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.ClassificationByUtility;
import org.fdryt.ornamental.domain.Plant;
import org.fdryt.ornamental.domain.Status;
import org.fdryt.ornamental.dto.product.ItemToListResponseDTO;
import org.fdryt.ornamental.dto.product.ProductResponseDTO;
import org.fdryt.ornamental.dto.product.SingleProductResponseDTO;
import org.fdryt.ornamental.problem.exception.DomainNotFoundException;
import org.fdryt.ornamental.repository.PlantRepository;
import org.fdryt.ornamental.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.fdryt.ornamental.utils.Utils.convertToEnum;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final PlantRepository plantRepository;
    private final ModelMapper productMapper;

    @Override
    public Page<ItemToListResponseDTO> findAllItemsToList(Pageable pageable) {
        Page<Plant> plantsObtainedPage = plantRepository.findAll(pageable);
        log.info("Item to list returned by number page: {} and with size: {}", plantsObtainedPage.getNumber(), plantsObtainedPage.getSize());

        List<ItemToListResponseDTO> list = plantsObtainedPage.stream()
                .map(plant -> productMapper.map(plant, ItemToListResponseDTO.class))
                .collect(Collectors.toCollection(ArrayList::new));

        return new PageImpl<>(list, pageable, plantsObtainedPage.getTotalElements());
    }

    @Override
    public Page<ItemToListResponseDTO> findAllItemsToListByStatus(String status, Pageable pageable) {
        Status enumStatus = convertToEnum(Status.class, status);
        Page<Plant> plantsObtainedPage = plantRepository.findAllByStatus(enumStatus, pageable);
        // TODO: do logs for each method used pagination
        List<ItemToListResponseDTO> list = plantsObtainedPage.stream()
                .map(plant -> productMapper.map(plant, ItemToListResponseDTO.class))
                .collect(Collectors.toCollection(ArrayList::new));

        return new PageImpl<>(list, pageable, plantsObtainedPage.getTotalElements());
    }

    @Override
    public Page<ProductResponseDTO> findAll(Pageable pageable) {
        Page<Plant> plantsObtainedPage = plantRepository.findAll(pageable);
        log.info("Products returned by page number: {} with size: {}", plantsObtainedPage.getNumber(), plantsObtainedPage.getSize());

        List<ProductResponseDTO> list = plantsObtainedPage.stream()
                .map(plant -> productMapper.map(plant, ProductResponseDTO.class))
                .collect(Collectors.toCollection(ArrayList::new));

        return new PageImpl<>(list, pageable, plantsObtainedPage.getTotalElements());
    }

    @Override
    public SingleProductResponseDTO findById(Integer id) {
        Plant plantObtained = plantRepository.findById(id)
                .orElseThrow(() -> new DomainNotFoundException(Plant.class, "ID", id));
        log.info("Plant returned with ID: {}", id);

        return productMapper.map(plantObtained, SingleProductResponseDTO.class);
    }

    @Override
    public Page<ProductResponseDTO> findAllByClassification(String classification, Pageable pageable) {
        ClassificationByUtility utility = convertToEnum(ClassificationByUtility.class, classification);
        Page<Plant> plantsObtainedPage = plantRepository.findAllByIdentificationClassifications(utility, pageable);
        log.info("Products returned with utility: {} by page number: {} with size: {}", classification, plantsObtainedPage.getNumber(), plantsObtainedPage.getSize());

        List<ProductResponseDTO> list = plantsObtainedPage.stream()
                .map(plant -> productMapper.map(plant, ProductResponseDTO.class))
                .collect(Collectors.toCollection(ArrayList::new));

        return new PageImpl<>(list, pageable, plantsObtainedPage.getTotalElements());
    }
}
