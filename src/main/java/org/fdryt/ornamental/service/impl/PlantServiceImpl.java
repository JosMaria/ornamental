package org.fdryt.ornamental.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.plant.*;
import org.fdryt.ornamental.dto.plant.CreatePlantDTO;
import org.fdryt.ornamental.dto.plant.PlantResponseDTO;
import org.fdryt.ornamental.dto.plant.SimpleInfoPlantResponseDTO;
import org.fdryt.ornamental.dto.plant.TechnicalSheetDTO;
import org.fdryt.ornamental.repository.FamilyJpaRepository;
import org.fdryt.ornamental.repository.PictureJpaRepository;
import org.fdryt.ornamental.repository.PlantJpaRepository;
import org.fdryt.ornamental.service.PlantService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlantServiceImpl implements PlantService {

    private final PlantJpaRepository plantJpaRepository;
    private final FamilyJpaRepository familyJpaRepository;
    private final PictureJpaRepository pictureJpaRepository;

    private static final String FOLDER_PATH = "/home/josmaria/pictures_nursery/";

    @Override
    public PlantResponseDTO create(final CreatePlantDTO payload) {
        if (plantJpaRepository.existsByCommonName(payload.commonName())) {
            throw new EntityExistsException("Planta nombrada: %s ya existe, no puede ser repetida.".formatted(payload.commonName()));
        }

        Family familyObtained = null;
        if (payload.familyName() != null) {
            familyObtained = familyJpaRepository
                .findByName(payload.familyName())
                .orElseThrow(() -> new EntityNotFoundException("Familia %s no fue encontrada.".formatted(payload.familyName())));
        }

        Plant plantToPersist = fromCreatePlantDtoToEntityPlant(payload, familyObtained);

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
    public String uploadImages(final MultipartFile pictureOne, final MultipartFile pictureTwo, final MultipartFile pictureThree) {
        System.out.println(pictureOne == null ? "picture one is null" : "picture one is not null");
        System.out.println(pictureTwo == null ? "picture two is null" : "picture two is not null");
        System.out.println(pictureThree == null ? "picture three is null" : "picture three is not null");
        /*String filePath = FOLDER_PATH + pictureOne.getOriginalFilename();
        Picture picturePersisted = pictureJpaRepository.save(
                Picture.builder()
                        .name(pictureOne.getOriginalFilename())
                        .type(pictureOne.getContentType())
                        .filePath(filePath)
                        .build()
        );
        try {
            pictureOne.transferTo(new File(filePath));
        } catch (Exception e) {
            log.warn(e.getMessage());
        }

        return "File %s uploaded successfully in the path %s".formatted(
                picturePersisted.getName(),
                picturePersisted.getFilePath()
        );*/
        return "";
    }

    @Override
    public byte[] downloadPicture(String pictureName) {
        Optional<Picture> optionalPictureObtained = pictureJpaRepository.findByName(pictureName);
        String filePath = optionalPictureObtained.orElseGet(() ->
                Picture.builder()
                        .filePath("")
                        .name("")
                        .type("")
                        .build()
        ).getFilePath();

        try {
            return Files.readAllBytes(new File(filePath).toPath());
        } catch (Exception e) {
            log.warn(e.getMessage());
        }

        return null;
    }

    // TODO: Create mapper
    private Plant fromCreatePlantDtoToEntityPlant(CreatePlantDTO dto, Family family) {
        ScientificName scientificName = new ScientificName(dto.scientificName(), dto.scientistLastnameInitial());
        FundamentalData fundamentalData = new FundamentalData();
        fundamentalData.setCommonName(dto.commonName());
        fundamentalData.setScientificName(scientificName);
        fundamentalData.setClassifications(dto.classifications());
        fundamentalData.setFamily(family);

        return Plant.builder()
                .fundamentalData(fundamentalData)
                .description(dto.description())
                .status(dto.status())
                .build();
    }

    private PlantResponseDTO fromPlantEntitytoPlantResponseDTO(Plant entity) {
        FundamentalData fundamentalData = entity.getFundamentalData();
        return new PlantResponseDTO(
                entity.getId(),
                fundamentalData.getCommonName(),
                fundamentalData.getScientificName().toString(),
                fundamentalData.getFamily().getName(),
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
