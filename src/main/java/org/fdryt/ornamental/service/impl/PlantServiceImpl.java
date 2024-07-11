package org.fdryt.ornamental.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.plant.*;
import org.fdryt.ornamental.dto.plant.*;
import org.fdryt.ornamental.repository.PictureJpaRepository;
import org.fdryt.ornamental.repository.PlantJpaRepository;
import org.fdryt.ornamental.service.PlantService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.fdryt.ornamental.constant.Constant.PHOTO_DIRECTORY;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlantServiceImpl implements PlantService {

    private final PlantJpaRepository plantJpaRepository;
    //private final FamilyJpaRepository familyJpaRepository;
    private final PictureJpaRepository pictureJpaRepository;

    private static final String FOLDER_PATH = "/home/josmaria/pictures_nursery/";

    @Override
    public PlantResponseDTO create(final CreatePlantDTO payload) {
        if (plantJpaRepository.existsByCommonName(payload.commonName())) {
            throw new EntityExistsException("Planta nombrada: %s ya existe, no puede ser repetida.".formatted(payload.commonName()));
        }
/*
        Family familyObtained = null;
        if (payload.familyName() != null) {
            familyObtained = familyJpaRepository
                .findByName(payload.familyName())
                .orElseThrow(() -> new EntityNotFoundException("Familia %s no fue encontrada.".formatted(payload.familyName())));
        }*/

        Plant plantToPersist = fromCreatePlantDtoToEntityPlant(payload);

        List<Detail> detailsToPersist = payload.details().stream()
                .map(detail -> Detail.builder().detail(detail).plant(plantToPersist).build())
                .collect(Collectors.toCollection(ArrayList::new));

        List<TechnicalSheet> technicalSheet = payload.technicalSheet().stream()
                .map(technicalSheetDTO -> TechnicalSheet.builder()
                        .word(technicalSheetDTO.word())
                        .info(technicalSheetDTO.info())
                        .plant(plantToPersist)
                        .build())
                .collect(Collectors.toCollection(ArrayList::new));

        plantToPersist.addDetails(detailsToPersist);
        plantToPersist.addTechnicalSheet(technicalSheet);

        Plant plantPersisted = plantJpaRepository.save(plantToPersist);
        log.info("plant persisted successfully with its ID: {}", plantPersisted.getId());

        return fromPlantEntitytoPlantResponseDTO(plantPersisted);
    }

    @Override
    public List<SimpleInfoPlantResponseDTO> getAllCommonName() {
        List<SimpleInfoPlantResponseDTO> allSimpleInfoPlant = plantJpaRepository.findAllSimpleInfoPlant();
        log.info("Fetch all common names of the plants");
        return allSimpleInfoPlant;
    }

    @Override
    public void delete(final Integer id) {
        // TODO: will done
        log.info("Plant with ID: {} deleted", id);
    }

    @Override
    public String uploadImageToFileSystem(final MultipartFile file, final Integer plantId) {
        String filePath = FOLDER_PATH + file.getOriginalFilename();

        try {
            File pictureToSave = new File(filePath);
            file.transferTo(pictureToSave);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return "Error upload file %s".formatted(file.getOriginalFilename());
        }

        Picture pictureToPersist = Picture.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath)
                .build();
        Picture picturePersisted = pictureJpaRepository.save(pictureToPersist);
        String message = "File named '%s' uploaded successfully in the directory %s".formatted(
                picturePersisted.getName(), FOLDER_PATH);
        log.info(message);
        return message;

    }

    @Override
    public byte[] downloadPictureFromFileSystem(String pictureName) {
        Optional<Picture> optionalPictureObtained = pictureJpaRepository.findByName(pictureName);
        Picture defaultPicture = Picture.builder()
                .filePath("")
                .name("")
                .type("")
                .build();
        String filePath = optionalPictureObtained.orElse(defaultPicture).getFilePath();

        try {
            return Files.readAllBytes(new File(filePath).toPath());
        } catch (Exception e) {
            log.warn(e.getMessage());
            return null;
        }
    }

    @Override
    public String updateInformationBasic(Integer id, UpdateInformationBasicDTO payload) {
        Plant plantObtained = plantJpaRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Plant with ID: %s does not exists.".formatted(id)));

        throwExceptionIfCommonNameIsInvalid(payload.commonName(), plantObtained.getFundamentalData().getCommonName());
        throwExceptionIfFamilyIsInvalid(payload.family());

        // update
        FundamentalData fundamentalData = plantObtained.getFundamentalData();
        if(!fundamentalData.getCommonName().equals(payload.commonName())) {
            fundamentalData.setCommonName(payload.commonName());
        }
/*
        if (payload.family() == null) {
            fundamentalData.setFamily(null);
        } else {
            Family familyObtained = familyJpaRepository
                    .findByName(payload.family())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Family with name: %s does not exists".formatted(payload.family())));
            fundamentalData.setFamily(familyObtained);
        }
        */

        plantObtained.getFundamentalData().getClassifications();

        return null;
    }

    @Override
    public String uploadPhoto(MultipartFile image) {
        Path fileStorageLocation = Paths.get(PHOTO_DIRECTORY).toAbsolutePath().normalize();

        try {
            if (!Files.exists(fileStorageLocation)) {
                Files.createDirectories(fileStorageLocation);
            }

            Files.copy(
                    image.getInputStream(),
                    fileStorageLocation.resolve("filename"),
                    REPLACE_EXISTING
            );

            return ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/contacts/image/" + "filename")
                    .toUriString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void throwExceptionIfCommonNameIsInvalid(String newCommonName, String oldCommonName) {
        if (newCommonName != null) {
            if (!newCommonName.equals(oldCommonName) && plantJpaRepository.existsByCommonName(oldCommonName)) {
                throw new IllegalArgumentException("Common name \"%s\" already exists in other plant.");
            }
        } else {
            throw new IllegalArgumentException("common name must have a value");
        }
    }

    private void throwExceptionIfFamilyIsInvalid(String newFamily) {
        /*if (newFamily != null) {
            if (!familyJpaRepository.existsByName(newFamily)) {
                throw new EntityNotFoundException("Family with name: %s does not exists".formatted(newFamily));
            }
        }*/
    }

    // TODO: Create mapper
    private Plant fromCreatePlantDtoToEntityPlant(CreatePlantDTO dto) {
        FundamentalData fundamentalData = new FundamentalData();
        fundamentalData.setCommonName(dto.commonName());
        fundamentalData.setClassifications(dto.classifications());

        return Plant.builder()
                .fundamentalData(fundamentalData)
                .description(dto.description())
                .status(dto.status())
                .price(dto.price())
                .build();
    }

    private PlantResponseDTO fromPlantEntitytoPlantResponseDTO(Plant entity) {
        FundamentalData fundamentalData = entity.getFundamentalData();
        return new PlantResponseDTO(
                entity.getId(),
                fundamentalData.getCommonName(),
                "",
                null,
                fundamentalData.getClassifications(),
                entity.getStatus(),
                entity.getDescription(),
                entity.getDetails().stream().map(Detail::getDetail).collect(Collectors.toCollection(ArrayList::new)),
                entity.getTechnicalSheets().stream()
                        .map(item -> new TechnicalSheetDTO(item.getWord(), item.getInfo()))
                        .collect(Collectors.toCollection(ArrayList::new))
        );
    }
}
