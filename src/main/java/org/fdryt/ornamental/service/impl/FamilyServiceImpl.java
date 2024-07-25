package org.fdryt.ornamental.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.plant.Family;
import org.fdryt.ornamental.dto.family.FamilyRequestDTO;
import org.fdryt.ornamental.dto.family.FamilyResponseDTO;
import org.fdryt.ornamental.repository.FamilyJpaRepository;
import org.fdryt.ornamental.service.FamilyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FamilyServiceImpl implements FamilyService {

    private final FamilyJpaRepository familyJpaRepository;

    @Override
    public FamilyResponseDTO create(final FamilyRequestDTO payload) {
        throwExceptionIfNameAlreadyExists(payload.name());
        Family familyPersisted = familyJpaRepository.save(mapToFamilyEntity(payload));
        log.info("Family persisted with the name: {}", familyPersisted.getName());
        return mapToFamilyResponseDTO(familyPersisted);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public List<FamilyResponseDTO> createMany(final Set<FamilyRequestDTO> families) {
        return families.stream()
                .map(this::create)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<FamilyResponseDTO> obtainAll() {
        List<FamilyResponseDTO> familiesObtained = familyJpaRepository.findAllFamilies();
        log.info("Obtained everyone of the families.");
        return familiesObtained;
    }

    @Override
    public FamilyResponseDTO deleteByID(final String id) {
        Family family = obtainFamilyOrThrowException(id);
        familyJpaRepository.deleteById(id);
        log.info("Family removed with name: {}.", family.getName());
        return mapToFamilyResponseDTO(family);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public FamilyResponseDTO modifyNameByID(final String id, final FamilyRequestDTO payload) {
        throwExceptionIfNameAlreadyExists(payload.name());
        Family family = obtainFamilyOrThrowException(id);
        family.setName(payload.name());
        log.info("Family updated with new name: {}", family.getName());
        return mapToFamilyResponseDTO(family);
    }

    // Methods utils
    private void throwExceptionIfNameAlreadyExists(String name) {
        if (familyJpaRepository.existsByName(name)) {
            String message = String.format("Family with name %s already exists.", name);
            log.warn(message);
            throw new EntityExistsException(message);
        }
    }

    private Family obtainFamilyOrThrowException(String id) {
        return familyJpaRepository.findById(id)
                .orElseThrow(() -> {
                    String message = String.format("Family with ID %s not found.", id);
                    log.warn(message);
                    return new EntityNotFoundException(message);
                });
    }

    // Mappers
    private Family mapToFamilyEntity(FamilyRequestDTO payload) {
        return Family.builder()
                .name(payload.name())
                .build();
    }

    private FamilyResponseDTO mapToFamilyResponseDTO(Family family) {
        return new FamilyResponseDTO(family.getId(), family.getName());
    }
}
