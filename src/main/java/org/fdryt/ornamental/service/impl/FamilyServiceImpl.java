package org.fdryt.ornamental.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.plant.alternative.Family;
import org.fdryt.ornamental.dto.alternative.FamilyRequestDTO;
import org.fdryt.ornamental.dto.alternative.FamilyResponseDTO;
import org.fdryt.ornamental.exception.FamilyNotFoundException;
import org.fdryt.ornamental.exception.RepeatedFamilyNameException;
import org.fdryt.ornamental.repository.FamilyJpaRepository;
import org.fdryt.ornamental.service.FamilyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FamilyServiceImpl implements FamilyService {

    private final FamilyJpaRepository familyJpaRepository;

    @Override
    public FamilyResponseDTO create(final FamilyRequestDTO payload) {
        throwExceptionIfFamilyNameAlreadyExists(payload.name());
        Family familyPersisted = familyJpaRepository.save(toFamilyEntity(payload));
        log.info("Family saved with name: {}", familyPersisted.getName());
        return toFamilyResponseDTO(familyPersisted);
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
    public FamilyResponseDTO deleteFamilyByID(final String id) {
        Family familyObtained = throwExceptionIfFamilyNotFound(id);
        familyJpaRepository.deleteById(id);
        log.info("Family removed with name: {}.", familyObtained.getName());

        return toFamilyResponseDTO(familyObtained);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public FamilyResponseDTO modifyFamilyNameByID(final String id, final FamilyRequestDTO payload) {
        throwExceptionIfFamilyNameAlreadyExists(payload.name());
        Family familyObtained = throwExceptionIfFamilyNotFound(id);
        familyObtained.setName(payload.name());
        log.info("Family updated with new name: {}", familyObtained.getName());
        return toFamilyResponseDTO(familyObtained);
    }

    // Methods utils
    private Family throwExceptionIfFamilyNotFound(final String id) {
        return familyJpaRepository.findById(id)
                .orElseThrow(() -> new FamilyNotFoundException(id));
    }

    private void throwExceptionIfFamilyNameAlreadyExists(final String name) {
        if (familyJpaRepository.existsByName(name)) {
            var exception = new RepeatedFamilyNameException(name);
            log.warn(exception.getMessage());
            throw exception;
        }
    }

    // Mappers
    private Family toFamilyEntity(FamilyRequestDTO payload) {
        return Family.builder()
                .name(payload.name())
                .build();
    }

    private FamilyResponseDTO toFamilyResponseDTO(Family family) {
        return new FamilyResponseDTO(family.getId(), family.getName());
    }
}
