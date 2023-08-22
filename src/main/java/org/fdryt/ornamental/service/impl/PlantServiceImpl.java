package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.plant.*;
import org.fdryt.ornamental.dto.plant.CreatePlantDTO;
import org.fdryt.ornamental.dto.plant.PlantResponseDTO;
import org.fdryt.ornamental.repository.FamilyRepository;
import org.fdryt.ornamental.repository.PlantRepository;
import org.fdryt.ornamental.service.PlantService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;
    private final FamilyRepository familyRepository;

    @Override
    public PlantResponseDTO create(final CreatePlantDTO payload) {
        // common name is unique so verify if exists
        if (plantRepository.existsByCommonName(payload.commonName())) {
            // TODO: change exception or create new type exception
            throw new IllegalArgumentException("Plant with this common name: %s already exists.".formatted(payload.commonName()));
        }

        Plant plantToPersist = toEntityPlant(payload);
        Plant plantPersisted = plantRepository.add(plantToPersist);
        log.info("plant persisted successfully with its ID: {}", plantPersisted.getId());

        return toPlantResponseDTO(plantPersisted);
    }

    @Override
    public List<PlantResponseDTO> createAll(final List<CreatePlantDTO> plants) {
        return plants.stream()
                .map(this::create)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Family findFamilyByNameOrElseThrowException(String name) {
        Family familyObtained = null;

        if (name != null) {
            familyObtained = familyRepository.findByName(name)
                    .orElseThrow(() -> new IllegalArgumentException("Family with name %s does not found.".formatted(name)));
        }

        return familyObtained;
    }
    @Override
    public void delete(final Integer id) {
        // TODO: will done
        log.info("Plant with ID: {} deleted", id);
    }

    // create mapper to this
    private Plant toEntityPlant(CreatePlantDTO dto) {
        Family familyObtained = findFamilyByNameOrElseThrowException(dto.nameFamily());

        ScientificName scientificName = new ScientificName(dto.scientificName(), dto.scientistLastnameInitial());
        FundamentalData fundamentalData = new FundamentalData();
        fundamentalData.setCommonName(dto.commonName());
        fundamentalData.setScientificName(scientificName);
        fundamentalData.setClassifications(dto.classifications());
        fundamentalData.setFamily(familyObtained);

        AdditionalData additionalData = new AdditionalData();
        additionalData.setDetails(dto.details());
        additionalData.setNotes(dto.notes());

        return Plant.builder()
                .fundamentalData(fundamentalData)
                .additionalData(additionalData)
                .status(dto.status())
                .build();
    }

    private PlantResponseDTO toPlantResponseDTO(Plant entity) {
        PlantResponseDTO plantResponseDTO = new PlantResponseDTO();
        plantResponseDTO.setId(entity.getId());
        plantResponseDTO.setCommonName(entity.getFundamentalData().getCommonName());
        plantResponseDTO.setScientificName(entity.getFundamentalData().getScientificName().toString());
        plantResponseDTO.setFamily(entity.getFundamentalData().getFamily().getName());
        plantResponseDTO.setClassifications(entity.getFundamentalData().getClassifications());
        plantResponseDTO.setStatus(entity.getStatus());
        plantResponseDTO.setDetails(entity.getAdditionalData().getDetails());
        plantResponseDTO.setNotes(entity.getAdditionalData().getNotes());

        return plantResponseDTO;
    }
}
