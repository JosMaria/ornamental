package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.domain.OrnamentalPlant;
import org.fdryt.ornamental.dto.ProductResponseDTO;
import org.fdryt.ornamental.repository.OrnamentalPlantRepository;
import org.fdryt.ornamental.service.OrnamentalPlantService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrnamentalPlantServiceImpl implements OrnamentalPlantService {

    private final OrnamentalPlantRepository ornamentalPlantRepository;
    private final ModelMapper ornamentalPlantMapper;

    @Override
    public List<ProductResponseDTO> getOrnamentalPlants(Pageable pageable) {
        List<OrnamentalPlant> ornamentalPlantsObtained = ornamentalPlantRepository.findAll();
        return ornamentalPlantsObtained.stream()
                .map(ornamentalPlant -> ornamentalPlantMapper.map(ornamentalPlant, ProductResponseDTO.class))
                .toList();
    }
}
