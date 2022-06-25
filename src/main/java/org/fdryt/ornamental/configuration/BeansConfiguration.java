package org.fdryt.ornamental.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

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
