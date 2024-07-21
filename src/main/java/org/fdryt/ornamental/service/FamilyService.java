package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.family.FamilyRequestDTO;
import org.fdryt.ornamental.dto.family.FamilyResponseDTO;

import java.util.List;
import java.util.Set;

public interface FamilyService {

    FamilyResponseDTO create(FamilyRequestDTO payload);

    List<FamilyResponseDTO> createMany(Set<FamilyRequestDTO> payload);

    List<FamilyResponseDTO> obtainAll();

    FamilyResponseDTO deleteByID(String id);

    FamilyResponseDTO modifyNameByID(String id, FamilyRequestDTO payload);
}
