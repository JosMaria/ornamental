package org.fdryt.ornamental.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.plant.alternative.FamilyV2;
import org.fdryt.ornamental.dto.alternative.FamilyRequestDTO;
import org.fdryt.ornamental.dto.alternative.FamilyResponseDTO;
import org.fdryt.ornamental.repository.FamilyJpaRepositoryV2;
import org.fdryt.ornamental.service.FamilyServiceV2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FamilyServiceImplV2 implements FamilyServiceV2 {

    private final FamilyJpaRepositoryV2 familyJpaRepository;

    @Override
    public FamilyResponseDTO createFamily(final FamilyRequestDTO payload) {
        // TODO: handle exceptions
        if (familyJpaRepository.existsByName(payload.name())) {
            String message = "family name: %s already exists.".formatted(payload.name());
            throw new EntityExistsException(message);
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

    // Methods utils

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
