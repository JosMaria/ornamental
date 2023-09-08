package org.fdryt.ornamental.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
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
        // verify common name does not repeat
        if (plantRepository.existsByCommonName(payload.commonName())) {
            throw new EntityExistsException("Planta nombrada: %s ya existe, no puede ser repetida.".formatted(payload.commonName()));
        }

        // verify family exists
        Family familyFounded = null;
        if (payload.nameFamily() != null) {
            familyFounded = familyRepository
                .findByName(payload.nameFamily())
                .orElseThrow(() -> new EntityNotFoundException("Familia %s no fue encontrada.".formatted(payload.nameFamily())));
        }

        Plant plantToPersist = fromCreatePlantDtoToEntityPlant(payload, familyFounded);

        List<Note> notesToPersist = payload.notes().stream()
                .map(note -> Note.builder().note(note).plant(plantToPersist).build())
                .collect(Collectors.toCollection(ArrayList::new));

        List<Detail> detailsToPersist = payload.details().stream()
                .map(detail -> Detail.builder().detail(detail).plant(plantToPersist).build())
                .collect(Collectors.toCollection(ArrayList::new));

        List<TechnicalSheet> technicalSheet = payload.technicalSheet().stream()
                .map(technicalSheetDTO -> TechnicalSheet.builder()
                        .word(technicalSheetDTO.word())
                        .info(technicalSheetDTO.info())
                        .plant(plantToPersist)
                        .build())
                .collect(Collectors.toCollection(ArrayList::new));

        plantToPersist.addNotes(notesToPersist);
        plantToPersist.addDetails(detailsToPersist);
        plantToPersist.addTechnicalSheet(technicalSheet);

        Plant plantPersisted = plantRepository.add(plantToPersist);
        log.info("plant persisted successfully with its ID: {}", plantPersisted.getId());

        return fromPlantEntitytoPlantResponseDTO(plantPersisted);
    }

    @Override
    public List<PlantResponseDTO> createAll(final List<CreatePlantDTO> plants) {
        return plants.stream()
                .map(this::create)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void delete(final Integer id) {
        // TODO: will done
        log.info("Plant with ID: {} deleted", id);
    }

    // TODO: Create mapper
    private Plant fromCreatePlantDtoToEntityPlant(CreatePlantDTO dto, Family family) {
        ScientificName scientificName = new ScientificName(dto.scientificName(), dto.scientistLastnameInitial());
        FundamentalData fundamentalData = new FundamentalData();
        fundamentalData.setCommonName(dto.commonName());
        fundamentalData.setScientificName(scientificName);
        fundamentalData.setClassifications(dto.classifications());
        fundamentalData.setFamily(family);

        return Plant.builder()
                .fundamentalData(fundamentalData)
                .status(dto.status())
                .build();
    }

    private PlantResponseDTO fromPlantEntitytoPlantResponseDTO(Plant entity) {
        PlantResponseDTO plantResponseDTO = new PlantResponseDTO();
        plantResponseDTO.setId(entity.getId());
        plantResponseDTO.setCommonName(entity.getFundamentalData().getCommonName());
        plantResponseDTO.setScientificName(entity.getFundamentalData().getScientificName().toString());
        plantResponseDTO.setFamily(entity.getFundamentalData().getFamily().getName());
        plantResponseDTO.setClassifications(entity.getFundamentalData().getClassifications());
        plantResponseDTO.setStatus(entity.getStatus());

        return plantResponseDTO;
    }
}
