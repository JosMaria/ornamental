package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.plant.Family;
import org.fdryt.ornamental.dto.family.CreateFamilyDTO;
import org.fdryt.ornamental.dto.family.FamilyResponseDTO;
import org.fdryt.ornamental.problem.exception.EntityAlreadyException;
import org.fdryt.ornamental.repository.FamilyRepository;
import org.fdryt.ornamental.service.FamilyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class FamilyServiceImpl implements FamilyService {

    private final FamilyRepository familyRepository;

    // TODO: implement of other matter
    @Override
    public List<FamilyResponseDTO> createAllByName(final List<CreateFamilyDTO> payload) {
        payload.forEach(dto -> verifyIfFamilyNameExists(dto.name()));

        Collection<Family> familiesToPersist = payload.stream()
                .map(this::fromCreateFamilyDtoToFamily)
                .collect(Collectors.toCollection(ArrayList::new));

        Collection<Family> familiesPersisted = familyRepository.addAll(familiesToPersist);
        log.info("All families were persisted.");

        return familiesPersisted.stream()
                .map(this::fromFamilytoFamilyResponseDTO)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<String> getAllNames() {
        List<String> allNames = familyRepository.getAllNames();
        log.info("Fetch all names of the families");

        return allNames;
    }

    private void verifyIfFamilyNameExists(String name) {
        if (familyRepository.existsByName(name)) {
            throw new EntityAlreadyException(Family.class, name);
        }
    }

    // TODO: Implement MAPPER better solution
    private Family fromCreateFamilyDtoToFamily(CreateFamilyDTO dto) {
        Family family = new Family();
        family.setName(dto.name().toLowerCase());
        return family;
    }

    private FamilyResponseDTO fromFamilytoFamilyResponseDTO(Family entity) {
        return new FamilyResponseDTO(
                entity.getId(),
                entity.getName()
        );
    }
}
