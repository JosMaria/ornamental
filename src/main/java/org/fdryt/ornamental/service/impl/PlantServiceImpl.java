package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.domain.ClassificationByUtility;
import org.fdryt.ornamental.domain.Identification;
import org.fdryt.ornamental.domain.Picture;
import org.fdryt.ornamental.domain.Plant;
import org.fdryt.ornamental.dto.ProductResponseDTO;
import org.fdryt.ornamental.dto.identification.ItemToListResponseDTO;
import org.fdryt.ornamental.repository.PlantRepository;
import org.fdryt.ornamental.service.PlantService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;
    private final ModelMapper ornamentalPlantMapper;

    @Override
    public List<ProductResponseDTO> findAllOrnamentalPlantsByClassification(String type, Pageable pageable) {
        ClassificationByUtility enumType = ClassificationByUtility.valueOf(type.trim().toUpperCase());
        return plantRepository.findAllByClassification(enumType, pageable).toList();
    }

    @Override
    public List<ProductResponseDTO> findAllOrnamentalPlants(Pageable pageable) {
        return plantRepository.findAll(pageable)
                .stream()
                .map(this::plantToProductResponseDTO)
                .toList();
    }

    @Override
    public List<ItemToListResponseDTO> findAllItemsToList(Pageable pageable) {
        return plantRepository.findAll(pageable)
                .stream()
                .map(this::plantToItemToListResponseDTO)
                .toList();
    }

    @Override
    public ProductResponseDTO findProductById(Integer id) {
        Plant plant = plantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Plant with ID: %s does not exists", id)));
        return plantToProductResponseDTO(plant);

    }

    private ItemToListResponseDTO plantToItemToListResponseDTO(Plant entity) {
        Identification identification = entity.getIdentification();
        return ItemToListResponseDTO.builder()
                .id(entity.getId())
                .commonName(identification.getCommonName())
                .scientificName(identification.getScientificName())
                .plusScientificName(identification.getPlusScientificName())
                .familyName(identification.getFamily().getName())
                .status(identification.getPlant().getStatus())
                .build();
    }

    private ProductResponseDTO plantToProductResponseDTO(Plant entity) {
        Identification identification = entity.getIdentification();
        return ProductResponseDTO.builder()
                .id(entity.getId())
                .commonName(identification.getCommonName())
                .scientificName(identification.getScientificName())
                .plusScientificName(identification.getPlusScientificName())
                .familyName(identification.getFamily().getName())
                .status(identification.getPlant().getStatus())
                .firstUrlPicture(
                    entity.getPictures().stream()
                        .findFirst()
                        .map(Picture::getUrl)
                        .orElse("https://picture-404")
                )
                .build();
    }
}
