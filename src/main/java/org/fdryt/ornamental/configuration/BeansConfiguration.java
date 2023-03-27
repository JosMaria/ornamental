package org.fdryt.ornamental.configuration;

import org.fdryt.ornamental.domain.Plant;
import org.fdryt.ornamental.dto.ProductResponseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    /*@Bean("plantMapper")
    public ModelMapper plantMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        Converter<Set<Classification>, Set<ClassificationByUtility>> converter =
                mappingContext -> mappingContext.getSource().stream()
                        .map(Classification::getClassificationByUtility)
                        .collect(Collectors.toSet());

        TypeMap<Plant, PlantResponseDTO> typeMap = modelMapper.createTypeMap(Plant.class, PlantResponseDTO.class);
        typeMap.addMappings(mapper -> mapper.using(converter).map(Plant::getClassifications, PlantResponseDTO::setClassifications));
        return modelMapper;
    }*/
}
