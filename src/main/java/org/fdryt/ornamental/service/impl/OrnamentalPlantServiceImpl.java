package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.domain.ClassificationByUtility;
import org.fdryt.ornamental.domain.OrnamentalPlant;
import org.fdryt.ornamental.dto.ProductResponseDTO;
import org.fdryt.ornamental.repository.OrnamentalPlantRepository;
import org.fdryt.ornamental.service.OrnamentalPlantService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrnamentalPlantServiceImpl implements OrnamentalPlantService {

    private final OrnamentalPlantRepository ornamentalPlantRepository;
    private final ModelMapper ornamentalPlantMapper;

    @Override
    public List<ProductResponseDTO> findAllOrnamentalPlants(Pageable pageable) {
        Page<OrnamentalPlant> ornamentalPlantsObtained = ornamentalPlantRepository.findAll(pageable);
        return ornamentalPlantsObtained.stream()
                .map(ornamentalPlant -> ornamentalPlantMapper.map(ornamentalPlant, ProductResponseDTO.class))
                .toList();
    }

    @Override
    public List<ProductResponseDTO> findAllOrnamentalPlantsByClassification(String type, Pageable pageable) {
        ClassificationByUtility enumType = ClassificationByUtility.valueOf(type.trim().toUpperCase());
        return ornamentalPlantRepository.findAllByClassification(enumType, pageable).toList();
    }
}
