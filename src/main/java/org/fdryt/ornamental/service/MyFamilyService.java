package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.family.CreateFamilyDTO;
import org.fdryt.ornamental.dto.family.FamilyResponseDTO;

import java.util.List;
import java.util.Set;

public interface MyFamilyService {

    FamilyResponseDTO create(CreateFamilyDTO payload);

    List<String> getAllNames();

    Set<FamilyResponseDTO> createAllByName(Set<String> names);
}
