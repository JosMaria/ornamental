package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.alternative.FamilyRequestDTO;
import org.fdryt.ornamental.dto.alternative.FamilyResponseDTO;

public interface FamilyServiceV2 {

    FamilyResponseDTO createFamily(FamilyRequestDTO payload);
}
