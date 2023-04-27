package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.Family;
import org.fdryt.ornamental.domain.Identification;
import org.fdryt.ornamental.domain.Picture;
import org.fdryt.ornamental.domain.Plant;
import org.fdryt.ornamental.dto.product.ProductResponseDTO;
import org.fdryt.ornamental.repository.PlantRepository;
import org.fdryt.ornamental.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final PlantRepository plantRepository;

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
                .plusScientificName(identification.getPlusScientificName())
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
