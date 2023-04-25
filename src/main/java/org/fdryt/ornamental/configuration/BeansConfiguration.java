package org.fdryt.ornamental.configuration;

import org.fdryt.ornamental.domain.*;
import org.fdryt.ornamental.dto.PlantResponseDTO;
import org.fdryt.ornamental.dto.ProductResponseDTO;
import org.fdryt.ornamental.dto.news.CreateNewsDTO;
import org.modelmapper.*;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.modelmapper.convention.MatchingStrategies.STRICT;

@Configuration
public class BeansConfiguration {

    @Bean("ornamentalPlantMapper")
    public ModelMapper ornamentalPlantMapper() {
        ModelMapper model = new ModelMapper();
        model.getConfiguration().setMatchingStrategy(STRICT);

        model.addMappings(new PropertyMap<Plant, ProductResponseDTO>() {
            @Override
            protected void configure() {

            }
        });
        return model;
    }

    @Bean("plantMapper")
    public ModelMapper plantMapper() {
        ModelMapper modelMapper = new ModelMapper();
        Converter<Set<Classification>, Set<String>> toSetClassifications = mappingContext ->
                mappingContext.getSource().stream()
                        .map(classification -> classification.getUtility().name())
                        .collect(Collectors.toSet());

        Converter<Identification, String> toScientificNameComplete = mappingContext -> {
            Identification source = mappingContext.getSource();
            boolean haveScientific = source.getPlusScientificName() != null;
            String formatToString = haveScientific ? "%s %s." : "%s";

            return format(formatToString,
                    source.getScientificName() != null ? source.getScientificName() : "",
                    haveScientific ? source.getPlusScientificName() : "");
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
                return new News(source.urlImage(), source.title(), source.description());
            }
        });

        return modelMapper;
    }
}
