package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.plant.FamilyRequestDTO;
import org.fdryt.ornamental.dto.plant.FamilyResponseDTO;
import org.fdryt.ornamental.dto.plant.PlantRequestDTO;
import org.fdryt.ornamental.dto.plant.PlantResponseDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface PlantService {

    PlantResponseDTO create(PlantRequestDTO payload);

    PlantResponseDTO deleteByID(Long id);

    void uploadImageToFileSystem(Long plantId, MultipartFile file);

    Resource downloadImageFromFileSystem(Long plantId, String imageId);

    FamilyResponseDTO createFamily(FamilyRequestDTO payload);

    List<FamilyResponseDTO> createManyFamilies(Set<FamilyRequestDTO> payload);

    List<FamilyResponseDTO> obtainAllFamilies();

    FamilyResponseDTO deleteFamilyByID(String id);

    FamilyResponseDTO modifyFamilyNameByID(String id, FamilyRequestDTO payload);
}
