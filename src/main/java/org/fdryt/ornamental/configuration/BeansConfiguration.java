package org.fdryt.ornamental.configuration;

import org.fdryt.ornamental.domain.Plant;
import org.fdryt.ornamental.dto.CreatePlantDTO;
import org.fdryt.ornamental.dto.PlantResponseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean("plantMapper")
    public ModelMapper plantMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);

        modelMapper.addMappings(new PropertyMap<Plant, PlantResponseDTO>() {
            @Override
            protected void configure() {
                skip(destination.getClassifications());
            }
        });

        modelMapper.addMappings(new PropertyMap<CreatePlantDTO, Plant>() {
            @Override
            protected void configure() {
                skip(destination.getClassifications());
            }
        });
        return modelMapper;
    }
}
