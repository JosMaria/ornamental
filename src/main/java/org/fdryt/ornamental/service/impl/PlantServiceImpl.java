package org.fdryt.ornamental.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.plant.*;
import org.fdryt.ornamental.dto.plant.CreatePlantDTO;
import org.fdryt.ornamental.dto.plant.PlantResponseDTO;
import org.fdryt.ornamental.dto.plant.TechnicalSheetDTO;
import org.fdryt.ornamental.repository.FamilyJpaRepository;
import org.fdryt.ornamental.repository.PlantJpaRepository;
import org.fdryt.ornamental.service.PlantService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlantServiceImpl implements PlantService {

    private final PlantJpaRepository plantJpaRepository;
    private final FamilyJpaRepository familyJpaRepository;

    @Override
    public PlantResponseDTO create(final CreatePlantDTO payload) {
        if (plantJpaRepository.existsByCommonName(payload.commonName())) {
            throw new EntityExistsException("Planta nombrada: %s ya existe, no puede ser repetida.".formatted(payload.commonName()));
        }

        Family familyObtained = null;
        if (payload.familyName() != null) {
            familyObtained = familyJpaRepository
                .findByName(payload.familyName())
                .orElseThrow(() -> new EntityNotFoundException("Familia %s no fue encontrada.".formatted(payload.familyName())));
        }

        Plant plantToPersist = fromCreatePlantDtoToEntityPlant(payload, familyObtained);

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

        Plant plantPersisted = plantJpaRepository.save(plantToPersist);
        log.info("plant persisted successfully with its ID: {}", plantPersisted.getId());

        return fromPlantEntitytoPlantResponseDTO(plantPersisted);
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
                .description(dto.description())
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
        plantResponseDTO.setDescription(entity.getDescription());
        plantResponseDTO.setNotes(entity.getNotes().stream().map(Note::getNote).collect(Collectors.toCollection(ArrayList::new)));
        plantResponseDTO.setDetails(entity.getDetails().stream().map(Detail::getDetail).collect(Collectors.toCollection(ArrayList::new)));
        plantResponseDTO.setTechnicalSheet(entity.getTechnicalSheets().stream()
                .map(item -> new TechnicalSheetDTO(item.getWord(), item.getInfo()))
                .collect(Collectors.toCollection(ArrayList::new)));
        return plantResponseDTO;
    }
}
