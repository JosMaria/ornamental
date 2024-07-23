package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.image.ImageMapping;
import org.fdryt.ornamental.dto.plant.PlantRequestDTO;
import org.fdryt.ornamental.dto.plant.PlantResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PlantService {

    PlantResponseDTO create(PlantRequestDTO payload);

    void uploadImageToFileSystem(String plantId, MultipartFile file);

    List<ImageMapping> downloadImageFromFileSystem(String plantId);

    void delete(Integer id);
}
