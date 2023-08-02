package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.plant.AdditionalData;
import org.fdryt.ornamental.domain.plant.FundamentalData;
import org.fdryt.ornamental.domain.plant.Family;
import org.fdryt.ornamental.domain.plant.MyPlant;
import org.fdryt.ornamental.domain.plant.ScientificName;
import org.fdryt.ornamental.dto.plant.CreatePlantDTO;
import org.fdryt.ornamental.dto.plant.PlantResponseDTO;
import org.fdryt.ornamental.repository.FamilyRepository;
import org.fdryt.ornamental.repository.MyPlantRepository;
import org.fdryt.ornamental.service.PlantService;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlantServiceImpl implements PlantService {

    private final MyPlantRepository plantRepository;
    private final FamilyRepository familyRepository;

    @Override
    public PlantResponseDTO create(final CreatePlantDTO payload) {
        // common name is unique so verify if exists
        if (plantRepository.existsByCommonName(payload.commonName())) {
            // TODO: change exception or create new type exception
            throw new IllegalArgumentException("Plant with this common name: %s already exists.".formatted(payload.commonName()));
        }

        // verify if given the family name exists
        Family familyObtained = null;
        if (payload.nameFamily() != null) {
            familyObtained = familyRepository.findByName(payload.nameFamily())
                    .orElseThrow(() -> new IllegalArgumentException("Family with name %s does not found.".formatted(payload.nameFamily())));
        }

        // create entity Plant to persist
        ScientificName scientificName = new ScientificName(payload.scientificName(), payload.scientistLastnameInitial());
        FundamentalData fundamentalData = new FundamentalData();
        fundamentalData.setCommonName(payload.commonName());
        fundamentalData.setScientificName(scientificName);
        fundamentalData.setClassifications(payload.classifications());
        fundamentalData.setFamily(familyObtained);

        AdditionalData additionalData = new AdditionalData();
        additionalData.setDetails(payload.details());
        additionalData.setNotes(payload.notes());

        MyPlant plantToPersist = MyPlant.builder()
                .fundamentalData(fundamentalData)
                .additionalData(additionalData)
                .status(payload.status())
                .build();

        MyPlant plantPersisted = plantRepository.add(plantToPersist);
        log.info("plant persisted successfully with its ID: {}", plantPersisted.getId());

        // map entity Plant to response DTO for the client
        PlantResponseDTO plantResponseDTO = new PlantResponseDTO();
        plantResponseDTO.setId(plantPersisted.getId());
        plantResponseDTO.setCommonName(plantPersisted.getFundamentalData().getCommonName());
        plantResponseDTO.setScientificName(plantPersisted.getFundamentalData().getScientificName().toString());
        plantResponseDTO.setFamily(plantPersisted.getFundamentalData().getFamily().getName());
        plantResponseDTO.setClassifications(plantPersisted.getFundamentalData().getClassifications());
        plantResponseDTO.setStatus(plantPersisted.getStatus());
        plantResponseDTO.setDetails(plantPersisted.getAdditionalData().getDetails());
        plantResponseDTO.setNotes(plantPersisted.getAdditionalData().getNotes());

        return plantResponseDTO;
    }

    @Override
    public void delete(final Integer id) {
        // TODO: will done
        log.info("Plant with ID: {} deleted", id);
    }
}
