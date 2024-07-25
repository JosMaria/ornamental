package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.plant.PlantRequestDTO;
import org.fdryt.ornamental.dto.plant.PlantResponseDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface PlantService {

    PlantResponseDTO create(PlantRequestDTO payload);

    void uploadImageToFileSystem(String plantId, MultipartFile file);

    Resource downloadImageFromFileSystem(String plantId);

    PlantResponseDTO deleteByID(String id);
}
