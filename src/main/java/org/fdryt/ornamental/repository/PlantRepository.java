package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.commons.repository.NurseryRepository;
import org.fdryt.ornamental.domain.plant.Plant;

public interface PlantRepository extends NurseryRepository<Plant, Integer> {

    boolean existsByCommonName(String commonName);
}
