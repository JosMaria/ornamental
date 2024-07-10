package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.alternative.FamilyRequestDTO;
import org.fdryt.ornamental.dto.alternative.FamilyResponseDTO;

import java.util.List;
import java.util.Set;

public interface FamilyService {

    FamilyResponseDTO create(FamilyRequestDTO payload);

    List<FamilyResponseDTO> createMany(Set<FamilyRequestDTO> payload);

    List<FamilyResponseDTO> obtainAll();

    FamilyResponseDTO deleteFamilyByID(String id);

    FamilyResponseDTO modifyFamilyNameByID(String id, FamilyRequestDTO payload);
}
