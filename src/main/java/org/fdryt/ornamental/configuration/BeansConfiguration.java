package org.fdryt.ornamental.configuration;

import org.fdryt.ornamental.domain.*;
import org.fdryt.ornamental.dto.news.CreateNewsDTO;
import org.fdryt.ornamental.dto.plant.PlantResponseDTO;
import org.fdryt.ornamental.dto.product.ItemToListResponseDTO;
import org.fdryt.ornamental.dto.product.ProductResponseDTO;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.modelmapper.convention.MatchingStrategies.STRICT;

@Configuration
public class BeansConfiguration {

    @Bean("plantMapper")
    public ModelMapper plantMapper() {
        ModelMapper modelMapper = new ModelMapper();
        Converter<Set<Classification>, Set<String>> toSetClassifications = mappingContext ->
                mappingContext.getSource().stream()
                        .map(classification -> classification.getUtility().name())
                        .collect(Collectors.toSet());

        Converter<Identification, String> toScientificNameComplete = mappingContext -> {
            Identification source = mappingContext.getSource();
            boolean haveScientific = source.getScientistSurnameInitial() != null;
            String formatToString = haveScientific ? "%s %s." : "%s";

            return format(formatToString,
                    source.getScientificName() != null ? source.getScientificName() : "",
                    haveScientific ? source.getScientistSurnameInitial() : "");
        };

        TypeMap<Plant, PlantResponseDTO> typeMap = modelMapper.createTypeMap(Plant.class, PlantResponseDTO.class);
        typeMap.addMapping(src -> src.getIdentification().getCommonName(), PlantResponseDTO::setCommonName);

        typeMap.addMappings(propertyMap -> propertyMap.using(toScientificNameComplete)
                .map(Plant::getIdentification, PlantResponseDTO::setScientificName));

        typeMap.addMapping(src -> {
            Family family = src.getIdentification().getFamily();
            return family != null ? family.getName() : "";
        }, PlantResponseDTO::setFamily);

        typeMap.addMappings(propertyMap -> propertyMap.using(toSetClassifications)
                .map(src -> src.getIdentification().getClassifications(), PlantResponseDTO::setClassifications));

        return modelMapper;
    }

    @Bean("newsMapper")
    public ModelMapper newsMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(STRICT);
        modelMapper.addConverter(new AbstractConverter<CreateNewsDTO, News>() {
            @Override
            protected News convert(CreateNewsDTO source) {
                return News.builder()
                        .urlImage(source.urlImage())
                        .title(source.title())
                        .description(source.description())
                        .build();
            }
        });

        return modelMapper;
    }

    @Bean("productMapper")
    public ModelMapper productMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(STRICT);

        Converter<Set<Picture>, String> toUrlFirstPicture = mappingContext ->
                mappingContext.getSource().stream()
                        .findFirst()
                        .map(Picture::getUrl)
                        .orElse("https://picture-404");

        modelMapper.createTypeMap(Plant.class, ItemToListResponseDTO.class)
                .addMapping(Plant::getId, ItemToListResponseDTO::setId)
                .addMapping(src -> src.getIdentification().getCommonName(), ItemToListResponseDTO::setCommonName)
                .addMapping(src -> src.getIdentification().getScientificName(), ItemToListResponseDTO::setScientificName)
                .addMapping(src -> src.getIdentification().getScientistSurnameInitial(), ItemToListResponseDTO::setScientistSurnameInitial)
                .addMapping(Plant::getStatus, ItemToListResponseDTO::setStatus)
                .addMapping(src -> {
                    Family family = src.getIdentification().getFamily();
                    return family != null ? family.getName() : "";
                }, ItemToListResponseDTO::setFamilyName);

        modelMapper.createTypeMap(Plant.class, ProductResponseDTO.class)
                .addMapping(Plant::getId, ItemToListResponseDTO::setId)
                .addMapping(src -> src.getIdentification().getCommonName(), ProductResponseDTO::setCommonName)
                .addMapping(src -> src.getIdentification().getScientificName(), ProductResponseDTO::setScientificName)
                .addMapping(src -> src.getIdentification().getScientistSurnameInitial(), ProductResponseDTO::setScientistSurnameInitial)
                .addMapping(Plant::getStatus, ProductResponseDTO::setStatus)
                .addMapping(src -> {
                    Family family = src.getIdentification().getFamily();
                    return family != null ? family.getName() : "";
                }, ProductResponseDTO::setFamilyName)
                .addMappings(propertyMap -> propertyMap.using(toUrlFirstPicture)
                        .map(Plant::getPictures, ProductResponseDTO::setFirstUrlPicture)
                );

        return modelMapper;
    }
}
