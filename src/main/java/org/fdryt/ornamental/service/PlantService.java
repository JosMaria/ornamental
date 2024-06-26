package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.plant.CreatePlantDTO;
import org.fdryt.ornamental.dto.plant.PlantResponseDTO;
import org.fdryt.ornamental.dto.plant.SimpleInfoPlantResponseDTO;
import org.fdryt.ornamental.dto.plant.UpdateInformationBasicDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PlantService {

    PlantResponseDTO create(CreatePlantDTO payload);

    List<SimpleInfoPlantResponseDTO> getAllCommonName();

    void delete(Integer id);

    String uploadImageToFileSystem(MultipartFile file, Integer plantId);

    byte[] downloadPictureFromFileSystem(String pictureName);

    String updateInformationBasic(Integer id, UpdateInformationBasicDTO payload);

    String uploadPhoto(MultipartFile file);
}
