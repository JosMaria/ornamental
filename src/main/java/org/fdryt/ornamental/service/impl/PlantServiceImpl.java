package org.fdryt.ornamental.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.plant.Family;
import org.fdryt.ornamental.domain.plant.Image;
import org.fdryt.ornamental.domain.plant.Plant;
import org.fdryt.ornamental.dto.plant.PlantRequestDTO;
import org.fdryt.ornamental.dto.plant.PlantResponseDTO;
import org.fdryt.ornamental.repository.FamilyJpaRepository;
import org.fdryt.ornamental.repository.ImageRepository;
import org.fdryt.ornamental.repository.PlantJpaRepository;
import org.fdryt.ornamental.service.PlantService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlantServiceImpl implements PlantService {

    private static final String FOLDER_PATH = "/home/josmaria/nursery/images/";

    private final ImageRepository imageRepository;
    private final PlantJpaRepository plantJpaRepository;
    private final FamilyJpaRepository familyJpaRepository;

    @Override
    public PlantResponseDTO create(final PlantRequestDTO payload) {
        if (plantJpaRepository.existsByCommonName(payload.commonName())) {
            String message = String.format("Plant named %s already exists", payload.commonName());
            log.warn(message);
            throw new EntityExistsException(message);

        } else {
            Family familyObtained = null;
            if (payload.familyName() != null) {
                familyObtained = familyJpaRepository.findByName(payload.familyName())
                        .orElseThrow(() -> {
                            String message = String.format("Family %s not found.", payload.familyName());
                            log.warn(message);
                            return new EntityExistsException(message);
                        });
            }

            Plant plantToPersist = Plant.builder()
                    .commonName(payload.commonName())
                    .scientificName(payload.scientificName())
                    .discoverer(payload.discoverer())
                    .status(payload.status())
                    .family(familyObtained)
                    .classifications(payload.classifications())
                    .build();

            Plant plantPersisted = plantJpaRepository.save(plantToPersist);
            log.info("Plant named: {} persisted successfully and has ID: {}",
                    plantPersisted.getCommonName(), plantPersisted.getId());
            return mapToPlantResponseDTO(plantPersisted);
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void uploadImageToFileSystem(final String plantId, final MultipartFile file) {
        Plant plantObtained = plantJpaRepository.findById(plantId)
                .orElseThrow(() -> {
                    String message = String.format("Plant with ID %s not found.", plantId);
                    log.warn(message);
                    return new EntityNotFoundException(message);
                });

        Path directory = Paths.get(FOLDER_PATH + plantId);
        Image imagePersisted = imageRepository.save(
                Image.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .path(directory.toAbsolutePath().toString())
                        .plant(plantObtained)
                        .build()
        );

        if (!Files.exists(directory)) {
            try {
                Files.createDirectory(directory);
                String fileName = imagePersisted.getId() + "_" + file.getOriginalFilename();
                Path filePath = directory.resolve(fileName);
                Files.write(filePath, file.getBytes());
                log.info("File named: {}, uploaded successfully in the folder {}", fileName, filePath.toAbsolutePath());

            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }

    @Override
    public byte[] downloadImageFromFileSystem(String plantId) {
        Image imageObtained = imageRepository.findById(1L)
                .orElseThrow(() -> {
                    String message = String.format("Image with ID %s not found.", 1L);
                    log.warn(message);
                    return new EntityExistsException(message);
                });

        try {
            File file = new File(FOLDER_PATH + imageObtained.getId() + "_" + imageObtained.getName());
            return Files.readAllBytes(file.toPath());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // mapper
    private PlantResponseDTO mapToPlantResponseDTO(Plant plant) {
        Family family = plant.getFamily();
        String familyName = family != null ? family.getName() : null;

        return new PlantResponseDTO(
                plant.getId(),
                plant.getCommonName(),
                plant.getScientificName(),
                plant.getDiscoverer(),
                plant.getStatus(),
                familyName,
                plant.getClassifications()
        );
    }

    @Override
    public void delete(final Integer id) {
        // TODO: will done
        log.info("Plant with ID: {} deleted", id);
    }
}
