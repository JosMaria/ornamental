package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.domain.ClassificationByUtility;
import org.fdryt.ornamental.domain.Identification;
import org.fdryt.ornamental.domain.Plant;
import org.fdryt.ornamental.dto.ProductResponseDTO;
import org.fdryt.ornamental.dto.identification.IdentificationResponseDTO;
import org.fdryt.ornamental.repository.IdentificationRepository;
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
    private final IdentificationRepository identificationRepository;
    private final ModelMapper ornamentalPlantMapper;

    @Override
    public List<ProductResponseDTO> findAllOrnamentalPlants(Pageable pageable) {
        Page<Plant> ornamentalPlantsObtained = plantRepository.findAll(pageable);
        return ornamentalPlantsObtained.stream()
                .map(ornamentalPlant -> ornamentalPlantMapper.map(ornamentalPlant, ProductResponseDTO.class))
                .toList();
    }

    @Override
    public List<ProductResponseDTO> findAllOrnamentalPlantsByClassification(String type, Pageable pageable) {
        ClassificationByUtility enumType = ClassificationByUtility.valueOf(type.trim().toUpperCase());
        return plantRepository.findAllByClassification(enumType, pageable).toList();
    }

    @Override
    public IdentificationResponseDTO test() {
        List<Identification> all = identificationRepository.findAll();
        System.out.println("value");
        return null;
    }
}
