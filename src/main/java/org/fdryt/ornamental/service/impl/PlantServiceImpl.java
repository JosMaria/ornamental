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
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
            if (payload.familyId() != null) {
                familyObtained = familyJpaRepository.findById(payload.familyId())
                        .orElseThrow(() -> {
                            String message = String.format("Family with ID %s has not been found", payload.familyId());
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
    public void uploadImageToFileSystem(final Long plantId, final MultipartFile file) {
        Plant plantObtained = plantJpaRepository.findById(plantId)
                .orElseThrow(() -> {
                    String message = String.format("Plant with ID %s not found.", plantId);
                    log.warn(message);
                    return new EntityNotFoundException(message);
                });

        try {
            Path directory = Paths.get(FOLDER_PATH + plantId);
            if (!Files.exists(directory)) {
                Files.createDirectory(directory);
            }

            Image imagePersisted = imageRepository.save(
                    Image.builder()
                            .name(file.getOriginalFilename())
                            .type(file.getContentType())
                            .path(directory.toAbsolutePath().toString())
                            .plant(plantObtained)
                            .build()
            );

            String fileName = imagePersisted.getId();
            Path filePath = directory.resolve(fileName);
            Files.write(filePath, file.getBytes());
            log.info("File named: {}, uploaded successfully in the folder {}", fileName, filePath.toAbsolutePath());

        } catch (IOException exception) {
            String message = "Could not upload image";
            log.warn(message);
            throw new RuntimeException(message);
        }
    }

    @Override
    public Resource downloadImageFromFileSystem(final Long plantId, final String imageId) {
        Resource resource = null;
        if (plantJpaRepository.existsById(plantId)) {
            Path directory = Paths.get(FOLDER_PATH + plantId);
            if (Files.exists(directory)) {
                Path filePath = Paths.get(FOLDER_PATH + plantId + "/").resolve(imageId).normalize();
                try {
                    resource = new UrlResource(filePath.toUri());
                    if (!resource.exists() || !resource.isReadable()) {
                        String message = "Could not download image";
                        log.warn(message);
                        throw new FileSystemNotFoundException(message);
                    }
                } catch (MalformedURLException exception) {
                    log.warn(exception.getMessage());
                    throw new FileSystemNotFoundException(exception.getMessage());
                }

            }
        }
        return resource;
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
    public PlantResponseDTO deleteByID(final Long id) {
        plantJpaRepository.deleteById(id);
        // TODO: will done
        return null;
    }
}
