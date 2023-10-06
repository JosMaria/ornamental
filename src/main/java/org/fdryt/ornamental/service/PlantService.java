package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.plant.CreatePlantDTO;
import org.fdryt.ornamental.dto.plant.PlantResponseDTO;
import org.fdryt.ornamental.dto.plant.SimpleInfoPlantResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PlantService {

    PlantResponseDTO create(CreatePlantDTO payload);

    List<SimpleInfoPlantResponseDTO> getAllCommonName();

    void delete(Integer id);

    String uploadImages(MultipartFile pictureOne, MultipartFile pictureTwo, MultipartFile pictureThree);

    byte[] downloadPicture(String pictureName);
}
