package org.fdryt.ornamental.configuration;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.auth.domain.User;
import org.fdryt.ornamental.auth.dto.RegisterRequestDTO;
import org.fdryt.ornamental.domain.*;
import org.fdryt.ornamental.domain.news.News;
import org.fdryt.ornamental.dto.family.CreateFamilyDTO;
import org.fdryt.ornamental.dto.news.CreateNewsDTO;
import org.fdryt.ornamental.dto.plant.PlantResponseDTO;
import org.fdryt.ornamental.dto.product.ItemToListResponseDTO;
import org.fdryt.ornamental.dto.product.ProductResponseDTO;
import org.fdryt.ornamental.dto.product.SingleProductResponseDTO;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.modelmapper.convention.MatchingStrategies.STRICT;

@RequiredArgsConstructor
@Configuration
public class BeansConfiguration {

    private final PasswordEncoder passwordEncoder;

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

        Converter<Set<Picture>, Set<String>> toUrlPictures = mappingContext ->
                mappingContext.getSource().stream()
                        .map(Picture::getUrl)
                        .collect(Collectors.toSet());

        Converter<Set<Classification>, Set<String>> toSetStrings = mappingContext ->
                mappingContext.getSource().stream()
                                .map(classification -> classification.getUtility().name())
                                .collect(Collectors.toCollection(HashSet::new));


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

        modelMapper.createTypeMap(Plant.class, SingleProductResponseDTO.class)
                .addMapping(Plant::getId, ItemToListResponseDTO::setId)
                .addMapping(src -> src.getIdentification().getCommonName(), SingleProductResponseDTO::setCommonName)
                .addMapping(src -> src.getIdentification().getScientificName(), SingleProductResponseDTO::setScientificName)
                .addMapping(src -> src.getIdentification().getScientistSurnameInitial(), SingleProductResponseDTO::setScientistSurnameInitial)
                .addMapping(Plant::getStatus, SingleProductResponseDTO::setStatus)
                .addMapping(src -> {
                    Family family = src.getIdentification().getFamily();
                    return family != null ? family.getName() : "";
                }, SingleProductResponseDTO::setFamilyName)
                .addMappings(propertyMap -> propertyMap.using(toUrlPictures)
                        .map(Plant::getPictures, SingleProductResponseDTO::setUrlPictures))
                .addMappings(propertyMap -> propertyMap.using(toSetStrings)
                        .map(source -> source.getIdentification().getClassifications(), SingleProductResponseDTO::setClassifications));

        return modelMapper;
    }
//
//    @Bean("familyMapper")
//    public ModelMapper familyMapper() {
//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration().setMatchingStrategy(STRICT);
//        modelMapper.addConverter(new AbstractConverter<CreateFamilyDTO, Family>() {
//            @Override
//            protected Family convert(CreateFamilyDTO createFamilyDTO) {
//                return Family.builder()
//                        .name(createFamilyDTO.name())
//                        .build();
//            }
//        });
//
//        return modelMapper;
//    }

    @Bean
    public ModelMapper userMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(STRICT);
        modelMapper.addConverter(new AbstractConverter<RegisterRequestDTO, User>() {
            @Override
            protected User convert(RegisterRequestDTO source) {
                return User.builder()
                        .username(source.username())
                        .password(passwordEncoder.encode(source.password()))
                        .isAccountNonExpired(true)
                        .isAccountNonLocked(true)
                        .isCredentialsNonExpired(true)
                        .isEnabled(true)
                        .role(source.role())
                        .build();
            }
        });

        return modelMapper;
    }
}
