package org.fdryt.ornamental.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.plant.alternative.FamilyV2;
import org.fdryt.ornamental.dto.alternative.FamilyRequestDTO;
import org.fdryt.ornamental.dto.alternative.FamilyResponseDTO;
import org.fdryt.ornamental.exception.FamilyNotFoundException;
import org.fdryt.ornamental.exception.RepeatedFamilyNameException;
import org.fdryt.ornamental.repository.FamilyJpaRepositoryV2;
import org.fdryt.ornamental.service.FamilyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FamilyServiceImpl implements FamilyService {

    private final FamilyJpaRepositoryV2 familyJpaRepository;

    @Override
    public FamilyResponseDTO createFamily(final FamilyRequestDTO payload) {
        if (familyJpaRepository.existsByName(payload.name())) {
            var exception = new RepeatedFamilyNameException(payload.name());
            log.info(exception.getMessage());
            throw exception;
        }

        log.info("Saving family with name: {}.", payload.name());
        FamilyV2 familyPersisted = familyJpaRepository.save(toFamilyEntity(payload));

        return toFamilyResponseDTO(familyPersisted);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public List<FamilyResponseDTO> createFamilies(final List<FamilyRequestDTO> families) {
        List<FamilyResponseDTO> familiesPersisted = new ArrayList<>();

        families.forEach((family) -> {
            FamilyResponseDTO response = createFamily(family);
            familiesPersisted.add(response);
        });

        return familiesPersisted;
    }

    @Override
    public List<FamilyResponseDTO> obtainFamilies() {
        List<FamilyV2> familiesObtained = familyJpaRepository.findAll();
        log.info("Obtained everyone of the families.");

        return familiesObtained.stream()
                .map(this::toFamilyResponseDTO)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public FamilyResponseDTO deleteFamilyByID(final String id) {
        FamilyV2 familyObtained = throwExceptionIfFamilyNotFound(id);
        log.info("Removing family with name: {}.", familyObtained.getName());
        familyJpaRepository.deleteById(id);

        return toFamilyResponseDTO(familyObtained);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public FamilyResponseDTO modifyFamilyByID(final String id, final FamilyResponseDTO payload) {
        if (familyJpaRepository.existsByName(payload.name())) {
            var exception = new RepeatedFamilyNameException(payload.name());
            log.info(exception.getMessage());
            throw exception;
        }

        FamilyV2 familyObtained = throwExceptionIfFamilyNotFound(id);
        familyObtained.setName(payload.name());
        log.info("Family name changed to '{}'", familyObtained.getName());
        return toFamilyResponseDTO(familyObtained);
    }

    // Methods utils
    private FamilyV2 throwExceptionIfFamilyNotFound(final String id) {
        return familyJpaRepository.findById(id)
                .orElseThrow(() -> new FamilyNotFoundException(id));
    }

    // Mappers
    private FamilyV2 toFamilyEntity(FamilyRequestDTO payload) {
        return FamilyV2.builder()
                .name(payload.name())
                .build();
    }

    private FamilyResponseDTO toFamilyResponseDTO(FamilyV2 family) {
        return new FamilyResponseDTO(family.getId(), family.getName());
    }
}
