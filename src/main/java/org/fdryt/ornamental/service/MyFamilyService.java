package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.family.CreateFamilyDTO;
import org.fdryt.ornamental.dto.family.FamilyResponseDTO;

public interface MyFamilyService {

    FamilyResponseDTO create(CreateFamilyDTO payload);
}
