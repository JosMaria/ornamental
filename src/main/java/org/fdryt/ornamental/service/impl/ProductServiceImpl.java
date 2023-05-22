package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.ClassificationByUtility;
import org.fdryt.ornamental.domain.Plant;
import org.fdryt.ornamental.domain.Status;
import org.fdryt.ornamental.dto.plant.PlantResponseDTO;
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
                .toList();

        return new PageImpl<>(list, pageable, plantsObtainedPage.getTotalElements());
    }

    @Override
    public List<ItemToListResponseDTO> findAllItemsToListByStatus(String status, Pageable pageable) {
        Status enumStatus = convertToEnum(Status.class, status);
        Page<Plant> plantsObtainedPage = plantRepository.findAllByStatus(enumStatus, pageable);

        return plantsObtainedPage.stream()
                .map(plant -> productMapper.map(plant, ItemToListResponseDTO.class))
                .toList();
    }

    @Override
    public List<ProductResponseDTO> findAll(Pageable pageable) {
        Page<Plant> plantsObtainedPage = plantRepository.findAll(pageable);
        log.info("Products returned by page number: {} with size: {}", plantsObtainedPage.getNumber(), plantsObtainedPage.getSize());

        return plantsObtainedPage.stream()
                    .map(plant -> productMapper.map(plant, ProductResponseDTO.class))
                    .toList();
    }

    @Override
    public SingleProductResponseDTO findById(Integer id) {
        Plant plantObtained = plantRepository.findById(id)
                .orElseThrow(() -> new DomainNotFoundException(Plant.class, "ID", id));
        log.info("Plant returned with ID: {}", id);

        return productMapper.map(plantObtained, SingleProductResponseDTO.class);
    }

    @Override
    public List<ProductResponseDTO> findAllByClassification(String classification, Pageable pageable) {
        ClassificationByUtility utility = convertToEnum(ClassificationByUtility.class, classification);
        Page<Plant> plantsObtainedPage = plantRepository.findAllByIdentificationClassifications(utility, pageable);
        log.info("Products returned with utility: {} by page number: {} with size: {}", classification, plantsObtainedPage.getNumber(), plantsObtainedPage.getSize());

        return plantsObtainedPage.stream()
                .map(plant -> productMapper.map(plant, ProductResponseDTO.class))
                .toList();
    }
}
