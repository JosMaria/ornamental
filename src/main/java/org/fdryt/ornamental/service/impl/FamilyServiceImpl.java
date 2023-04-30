package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.Family;
import org.fdryt.ornamental.dto.family.CreateFamilyDTO;
import org.fdryt.ornamental.dto.family.FamilyResponseDTO;
import org.fdryt.ornamental.problem.exception.EntityAlreadyException;
import org.fdryt.ornamental.repository.FamilyRepository;
import org.fdryt.ornamental.service.FamilyService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.lang.String.format;

@Slf4j
@RequiredArgsConstructor
@Service
public class FamilyServiceImpl implements FamilyService {

    private final FamilyRepository familyRepository;
    private final ModelMapper familyMapper;

    @Override
    public FamilyResponseDTO create(CreateFamilyDTO createFamilyDTO) {
        if (familyRepository.existsByName(createFamilyDTO.name())) {
            throw new EntityAlreadyException(Family.class, createFamilyDTO.name());
        }
        Family familyToPersist = familyMapper.map(createFamilyDTO, Family.class);
        Family familyPersisted = familyRepository.save(familyToPersist);
        log.info("Family with name: {} persisted", createFamilyDTO.name());

        return familyMapper.map(familyPersisted, FamilyResponseDTO.class);
    }

    @Override
    public Set<String> findAll() {
        Set<String> familiesObtained = familyRepository.findAllNames();
        log.info("All families returned");

        return familiesObtained;
    }
}
