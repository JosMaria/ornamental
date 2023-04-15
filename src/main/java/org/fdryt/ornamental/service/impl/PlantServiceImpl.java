package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.domain.*;
import org.fdryt.ornamental.dto.CreatePlantDTO;
import org.fdryt.ornamental.dto.ProductResponseDTO;
import org.fdryt.ornamental.dto.identification.ItemToListResponseDTO;
import org.fdryt.ornamental.repository.ClassificationRepository;
import org.fdryt.ornamental.repository.FamilyRepository;
import org.fdryt.ornamental.repository.PlantRepository;
import org.fdryt.ornamental.service.PlantService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.apache.commons.lang3.EnumUtils.getEnum;
import static org.apache.commons.lang3.EnumUtils.isValidEnum;

@RequiredArgsConstructor
@Service
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;
    private final FamilyRepository familyRepository;
    private final ClassificationRepository classificationRepository;
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
    public List<ProductResponseDTO> findAllByClassification(String type, Pageable pageable) {
        Predicate<Plant> existsPlantByClassification = plant ->
                plant.getIdentification().getClassifications()
                        .stream()
                        .anyMatch(classification -> classification.getUtility().getType().equals(type));

        return plantRepository.findAll()
                .stream()
                .filter(existsPlantByClassification)
                .map(this::plantToProductResponseDTO)
                .limit(15)
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
                .orElseThrow(() -> new IllegalArgumentException(format("Plant with ID: %s does not exists", id)));
        return plantToProductResponseDTO(plant);

    }

    @Override
    public ProductResponseDTO create(CreatePlantDTO createPlantDTO) {
        Family familyObtained = familyRepository.findByName(createPlantDTO.family())
                .orElseThrow(() -> new IllegalArgumentException(format("Family %s does not exist.", createPlantDTO.family())));

        if (!isValidEnum(Status.class, createPlantDTO.status())) {
            throw new IllegalArgumentException(format("Status %s does not valid.", createPlantDTO.status()));
        }
        Status status = getEnum(Status.class, createPlantDTO.status());

        Identification identification = new Identification(createPlantDTO.commonName(), createPlantDTO.scientificName(), createPlantDTO.lastNameScientific(), familyObtained);

        Set<Classification> classifications = createPlantDTO.classifications()
                .stream()
                .map(this::getClassification)
                .collect(Collectors.toSet());

        identification.addClassifications(classifications);
        Plant plant = new Plant(identification, status);

        Plant plantPersisted = plantRepository.save(plant);
        return plantToProductResponseDTO(plantPersisted);
    }

    private Classification getClassification(String classification) {
        if (!isValidEnum(ClassificationByUtility.class, classification)) {
            throw new IllegalArgumentException(format("type %s does not exist.", classification));
        }
        ClassificationByUtility utility = getEnum(ClassificationByUtility.class, classification);
        return classificationRepository.findByUtility(utility)
                .orElseThrow(() -> new IllegalArgumentException(format("%s does not founded.", utility)));
    }

    private ItemToListResponseDTO plantToItemToListResponseDTO(Plant entity) {
        Identification identification = entity.getIdentification();
        Family family = identification.getFamily();
        return ItemToListResponseDTO.builder()
                .id(entity.getId())
                .commonName(identification.getCommonName())
                .scientificName(identification.getScientificName())
                .plusScientificName(identification.getPlusScientificName())
                .familyName(family != null ? family.getName() : "")
                .status(identification.getPlant().getStatus())
                .build();
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
