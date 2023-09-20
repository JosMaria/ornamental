package org.fdryt.ornamental.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.plant.Family;
import org.fdryt.ornamental.dto.family.CreateFamilyDTO;
import org.fdryt.ornamental.dto.family.FamilyResponseDTO;
import org.fdryt.ornamental.repository.FamilyJpaRepository;
import org.fdryt.ornamental.repository.FamilyRepository;
import org.fdryt.ornamental.service.FamilyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class FamilyServiceImpl implements FamilyService {

    private final FamilyRepository familyRepository;
    private final FamilyJpaRepository familyJpaRepository;

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
        familyJpaRepository.deleteById(id);
        log.info("Family with ID: {} deleted", id);
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

    @Override
    public FamilyResponseDTO updateName(final Integer id, final CreateFamilyDTO payload) {
        Family familyFounded = familyRepository.findById(id)
                .orElseThrow((() -> new EntityNotFoundException("Familia %s con ID: %s no existe para actualizar.".formatted(payload.name(), id))));

        if (familyFounded.getName().equals(payload.name().trim())) {
            log.info("Familia: %s continua con el mismo nombre.".formatted(familyFounded.getName()));
            return fromFamilytoFamilyResponseDTO(familyFounded);
        }

        throwExceptionIfFamilyNameExists(payload.name());
        familyFounded.setName(payload.name());
        Family familyUpdated = familyRepository.update(familyFounded);
        log.info("Family with ID: {} change its name to {}.", familyUpdated.getId(), familyFounded.getName());

        return fromFamilytoFamilyResponseDTO(familyUpdated);
    }
}
