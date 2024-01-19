package org.fdryt.ornamental.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.plant.Family;
import org.fdryt.ornamental.dto.family.CreateFamilyDTO;
import org.fdryt.ornamental.dto.family.FamilyResponseDTO;
import org.fdryt.ornamental.repository.FamilyJpaRepository;
import org.fdryt.ornamental.repository.PlantJpaRepository;
import org.fdryt.ornamental.service.FamilyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class FamilyServiceImpl implements FamilyService {

    private final FamilyJpaRepository familyJpaRepository;
    private final PlantJpaRepository plantJpaRepository;

    @Override
    public List<FamilyResponseDTO> createAll(final List<CreateFamilyDTO> payload) {
        List<String> familyNames = payload.stream()
                .map(createFamilyDTO -> createFamilyDTO.name().toLowerCase().trim())
                .collect(Collectors.toCollection(ArrayList::new));

        familyNames.forEach(this::throwExceptionIfFamilyNameExists);
        List<Family> familiesToPersist = toCollectionFamilies(familyNames);
        List<Family> familiesPersisted = familyJpaRepository.saveAll(familiesToPersist);
        log.info("All families were persisted.");

        return toCollectionFamiliesResponseDTO(familiesPersisted);
    }

    @Override
    public List<FamilyResponseDTO> getAllFamilies() {
        List<FamilyResponseDTO> familiesObtained = familyJpaRepository.getAllFamilies();
        log.info("Fetch all the families");

        return familiesObtained;
    }

    @Override
    public void deleteById(final Integer id) {
        if (familyJpaRepository.existsById(id)) {
            int rowsAffected = plantJpaRepository.updatePlantFamilyIdToRemoveFamily(id);
            log.info("Count of plants %s are with value null in field family now.".formatted(rowsAffected));
            familyJpaRepository.deleteById(id);
            log.info("Family with ID: {} deleted", id);
        } else {
            throw new EntityNotFoundException("Familia con ID: %s ya fue eliminada.".formatted(id));
        }
    }

    @Override
    public FamilyResponseDTO updateName(final Integer id, final CreateFamilyDTO payload) {
        if (!familyJpaRepository.existsById(id)) {
            throw new EntityNotFoundException("Familia con ID: %s ya fue eliminada.".formatted(id));
        }
        String nameToUpdate = payload.name().toLowerCase().trim();
        throwExceptionIfFamilyNameExists(nameToUpdate);

        int rowsAffected = familyJpaRepository.updateFamilyName(id, nameToUpdate);
        if (rowsAffected < 1) {
            throw new EntityNotFoundException("No se actualizo ninguna familia con ID: %s".formatted(id));

        } else {
            log.info("Family with ID: {} change its name to {}.", id, nameToUpdate);
            return new FamilyResponseDTO(id, nameToUpdate);
        }
    }

    private void throwExceptionIfFamilyNameExists(String name) {
        if (familyJpaRepository.existsByName(name)) {
            throw new EntityExistsException("Familia nombrada %s ya existe.".formatted(name));
        }
    }

    private List<Family> toCollectionFamilies(List<String> familyNames) {
        return familyNames.stream()
                .map(this::fromCreateFamilyDtoToFamily)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Family fromCreateFamilyDtoToFamily(String familyName) {
        Family family = new Family();
        family.setName(familyName);
        return family;
    }

    private List<FamilyResponseDTO> toCollectionFamiliesResponseDTO(List<Family> families) {
        return families.stream()
                .map(this::fromFamilytoFamilyResponseDTO)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private FamilyResponseDTO fromFamilytoFamilyResponseDTO(Family entity) {
        return new FamilyResponseDTO(entity.getId(), entity.getName());
    }
}
