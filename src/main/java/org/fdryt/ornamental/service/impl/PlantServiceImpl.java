package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.*;
import org.fdryt.ornamental.dto.plant.CreatePlantDTO;
import org.fdryt.ornamental.dto.plant.PlantResponseDTO;
import org.fdryt.ornamental.problem.exception.DomainNotFoundException;
import org.fdryt.ornamental.repository.ClassificationRepository;
import org.fdryt.ornamental.repository.FamilyRepository;
import org.fdryt.ornamental.repository.PlantRepository;
import org.fdryt.ornamental.service.PlantService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.fdryt.ornamental.utils.Utils.convertToEnum;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;
    private final FamilyRepository familyRepository;
    private final ClassificationRepository classificationRepository;
    private final ModelMapper plantMapper;

    @Override
    public PlantResponseDTO create(final CreatePlantDTO createPlantDTO) {
        Status status = convertToEnum(Status.class, createPlantDTO.status());
        Family familyObtained = findFamilyByNameOrThrowException(createPlantDTO.family());
        Plant plantToPersist = createPlant(createPlantDTO, familyObtained, status);
        Plant plantPersisted = plantRepository.save(plantToPersist);
        log.info("Plant with ID: {} persisted", plantPersisted.getId());

        return plantMapper.map(plantPersisted, PlantResponseDTO.class);
    }

    @Override
    public void delete(final Integer id) {
        plantRepository.deleteById(id);
        log.info("Plant with ID: {} deleted", id);
    }

    @Override
    public List<PlantResponseDTO> createAll(List<CreatePlantDTO> list) {
        return list.stream()
                .map(this::create)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Family findFamilyByNameOrThrowException(String name) {
        return name != null ? familyRepository.findByName(name)
                .orElseThrow(() -> new DomainNotFoundException(Family.class, "name",  name)) : null;
    }

    private Plant createPlant(CreatePlantDTO createPlantDTO, Family family, Status status) {
        Identification identification = Identification.builder()
                .commonName(createPlantDTO.commonName())
                .scientificName(createPlantDTO.scientificName())
                .scientistSurnameInitial(createPlantDTO.scientistSurnameInitial())
                .family(family)
                .build();
        identification.addClassifications(convertClassifications(createPlantDTO.classifications()));

        return Plant.builder()
                .identification(identification)
                .status(status)
                .build();
    }

    private Set<Classification> convertClassifications(Set<String> classifications) {
        return classifications.stream()
                .map(this::findClassificationByUtility)
                .collect(Collectors.toSet());
    }

    private Classification findClassificationByUtility(String classification) {
        ClassificationByUtility utility = convertToEnum(ClassificationByUtility.class, classification);

        return classificationRepository.findByUtility(utility)
                .orElseThrow(() -> new DomainNotFoundException(Classification.class, classification, utility.name()));
    }
}
