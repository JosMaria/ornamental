package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.plant.alternative.PlantV3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantJpaRepositoryV2 extends JpaRepository<PlantV3, String> {
}
