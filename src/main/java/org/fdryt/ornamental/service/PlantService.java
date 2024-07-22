package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.plant.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PlantService {

    PlantResponseDTO create(PlantRequestDTO payload);

    void uploadImageToFileSystem(MultipartFile file);

    List<SimpleInfoPlantResponseDTO> getAllCommonName();

    void delete(Integer id);

    byte[] downloadPictureFromFileSystem(String pictureName);

    String updateInformationBasic(Integer id, UpdateInformationBasicDTO payload);

    String uploadPhoto(MultipartFile file);
}
