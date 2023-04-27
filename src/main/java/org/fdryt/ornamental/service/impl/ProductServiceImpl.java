package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.Family;
import org.fdryt.ornamental.domain.Identification;
import org.fdryt.ornamental.domain.Picture;
import org.fdryt.ornamental.domain.Plant;
import org.fdryt.ornamental.dto.product.ItemToListResponseDTO;
import org.fdryt.ornamental.dto.product.ProductResponseDTO;
import org.fdryt.ornamental.repository.PlantRepository;
import org.fdryt.ornamental.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final PlantRepository plantRepository;
    private final ModelMapper productMapper;

    @Override
    public List<ItemToListResponseDTO> findAllItemsToList(Pageable pageable) {
        Page<Plant> plantsObtainedPage = plantRepository.findAll(pageable);
        log.info("Item to list returned by number page: {} and with size: {}", plantsObtainedPage.getNumber(), plantsObtainedPage.getSize());

        return plantsObtainedPage.stream()
                .map(plant -> productMapper.map(plant, ItemToListResponseDTO.class))
                .toList();
    }

    @Override
    public List<ProductResponseDTO> findAll(Pageable pageable) {
        Page<Plant> page = plantRepository.findAll(pageable);
        log.info("Products returned by page number: {} with size: {}", page.getNumber(), page.getSize());

        return page.stream()
                    .map(this::plantToProductResponseDTO)
                    .toList();
    }

    private ProductResponseDTO plantToProductResponseDTO(Plant entity) {
        Identification identification = entity.getIdentification();
        Family family = identification.getFamily();
        return ProductResponseDTO.builder()
                .id(entity.getId())
                .commonName(identification.getCommonName())
                .scientificName(identification.getScientificName())
                .scientistSurnameInitial(identification.getScientistSurnameInitial())
                .familyName(family != null ? family.getName() : "")
                .status(entity.getStatus())
                .firstUrlPicture(
                        entity.getPictures().stream()
                                .findFirst()
                                .map(Picture::getUrl)
                                .orElse("https://picture-404")
                )
                .build();
    }
}
