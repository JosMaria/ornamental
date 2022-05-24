package org.fdryt.ornamental.configuration;

import org.fdryt.ornamental.domain.Classification;
import org.fdryt.ornamental.domain.Plant;
import org.fdryt.ornamental.domain.TypeClassification;
import org.fdryt.ornamental.dto.PlantResponseDTO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class BeansConfiguration {

    @Bean("plantMapper")
    public ModelMapper plantMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        Converter<Set<Classification>, Set<TypeClassification>> converter =
                mappingContext -> mappingContext.getSource().stream()
                        .map(Classification::getTypeClassification)
                        .collect(Collectors.toSet());

        TypeMap<Plant, PlantResponseDTO> typeMap = modelMapper.createTypeMap(Plant.class, PlantResponseDTO.class);
        typeMap.addMappings(mapper -> mapper.using(converter).map(Plant::getClassifications, PlantResponseDTO::setClassifications));
        return modelMapper;
    }
}
