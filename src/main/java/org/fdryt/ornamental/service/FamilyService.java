package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.family.CreateFamilyDTO;
import org.fdryt.ornamental.dto.family.FamilyResponseDTO;

import java.util.List;

public interface FamilyService {

    List<FamilyResponseDTO> createAll(List<CreateFamilyDTO> payload);

    List<FamilyResponseDTO> getAllFamilies();

    void deleteById(Integer id);

    FamilyResponseDTO updateName(Integer id, CreateFamilyDTO payload);
}
