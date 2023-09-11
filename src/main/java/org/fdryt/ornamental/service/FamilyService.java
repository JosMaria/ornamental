package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.family.CreateFamilyDTO;
import org.fdryt.ornamental.dto.family.FamilyResponseDTO;
import org.fdryt.ornamental.dto.family.UpdateFamilyDTO;

import java.util.List;

public interface FamilyService {

    List<FamilyResponseDTO> createAllByName(List<CreateFamilyDTO> payload);

    List<FamilyResponseDTO> getFamilies();

    void deleteById(Integer id);

    FamilyResponseDTO updateName(Integer id, UpdateFamilyDTO payload);
}
