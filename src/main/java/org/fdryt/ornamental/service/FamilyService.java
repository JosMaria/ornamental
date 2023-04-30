package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.family.CreateFamilyDTO;
import org.fdryt.ornamental.dto.family.FamilyResponseDTO;

import java.util.Set;

public interface FamilyService {

    Set<String> findAll();

    FamilyResponseDTO create(CreateFamilyDTO name);
}
