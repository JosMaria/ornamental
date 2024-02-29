package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.alternative.FamilyRequestDTO;
import org.fdryt.ornamental.dto.alternative.FamilyResponseDTO;

import java.util.List;

public interface FamilyServiceV2 {

    FamilyResponseDTO createFamily(FamilyRequestDTO payload);

    List<FamilyResponseDTO> createFamilies(List<FamilyRequestDTO> payload);

    List<FamilyResponseDTO> obtainFamilies();

    FamilyResponseDTO deleteFamilyByID(String id);

    FamilyResponseDTO modifyFamilyByID(String id, FamilyResponseDTO payload);
}
