package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.ProductResponseDTO;
import org.fdryt.ornamental.repository.OrnamentalPlantRepository;
import org.fdryt.ornamental.service.OrnamentalPlantService;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.fdryt.ornamental.domain.Family.*;

@RequiredArgsConstructor
@Service
public class OrnamentalPlantServiceImpl implements OrnamentalPlantService {

    private final OrnamentalPlantRepository ornamentalPlantRepository;

    @Override
    public List<ProductResponseDTO> getOrnamentalPlants() {
        ornamentalPlantRepository.findAll();
        return null;
    }

    private static final List<ProductResponseDTO> PRODUCTS = List.of(
            new ProductResponseDTO(1L, "Acaria orrida", "Acacia orrida", Fabaceae, "one", true),
            new ProductResponseDTO(2L, "Agave", "Agave tequilero", Asparagaceae, "two", false),
            new ProductResponseDTO(3L, "Aji ornamental", "Capsicum annuum L.", Solanaceae, "three", false),
            new ProductResponseDTO(4L, "Ajuga", "Ajuga reptans", Lamiaceae, "four", false),
            new ProductResponseDTO(5L, "Alamo", "Populus x canadensis moench", Salicaceae, "five", true),
            new ProductResponseDTO(6L, "Aloe", "Aloe barbadensis var. chinensis", Asphodelaceae, "six", false),
            new ProductResponseDTO(7L, "Amarilis", "Hippeastrum spp", Amaryllidaceae, "seven", false),
            new ProductResponseDTO(8L, "Amor de hombre", "Trasdecantia pallida", Commelinaceae, "eight", true),
            new ProductResponseDTO(9L, "Anturio", "Anthurium andreanum", Araceae, "nine", false),
            new ProductResponseDTO(10L, "Anturio gigante", "Anthurium andreanum", Araceae, "ten", false),
            new ProductResponseDTO(11L, "Aspidastra", "Aspidiastra elatior", Ruscaceae, "eleven", false),
            new ProductResponseDTO(12L, "Begonia rex", "Begonia rex", Begoniaceae, "twelve", false),
            new ProductResponseDTO(13L, "Begonias tuberosa", "Begonia x tuberhybrida", Begoniaceae, "thirteen", false),
            new ProductResponseDTO(14L, "Bingo de oro", "Duranta erecta", Verbenaceae, "fourteen", false));
}
