package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.Family;
import org.fdryt.ornamental.domain.plant.MyFamily;
import org.fdryt.ornamental.dto.family.CreateFamilyDTO;
import org.fdryt.ornamental.dto.family.FamilyResponseDTO;
import org.fdryt.ornamental.problem.exception.EntityAlreadyException;
import org.fdryt.ornamental.repository.MyFamilyRepository;
import org.fdryt.ornamental.service.MyFamilyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MyFamilyServiceImpl implements MyFamilyService {

    private final MyFamilyRepository familyRepository;

    @Override
    public FamilyResponseDTO create(final CreateFamilyDTO payload) {
        if (familyRepository.existsByName(payload.name())) {
            throw new EntityAlreadyException(Family.class, payload.name());
        }

        MyFamily familyToPersist = toMyFamily(payload);
        MyFamily familyPersisted = familyRepository.add(familyToPersist);
        log.info("Family with name: {} persisted", familyPersisted);

        return toFamilyResponseDTO(familyPersisted);
    }

    @Override
    public List<String> getAllNames() {
        List<String> allNames = familyRepository.getAllNames();
        log.info("Get all names of the families");

        return allNames;
    }

    // TODO: delete methods below when I create the mapper
    private MyFamily toMyFamily(CreateFamilyDTO dto) {
        MyFamily family = new MyFamily();
        family.setName(dto.name());

        return family;
    }

    private FamilyResponseDTO toFamilyResponseDTO(MyFamily entity) {
        FamilyResponseDTO response = new FamilyResponseDTO();
        response.setId(entity.getId());
        response.setName(entity.getName());

        return response;
    }
}
