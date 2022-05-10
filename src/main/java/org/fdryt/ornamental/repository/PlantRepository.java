package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlantRepository extends JpaRepository<Plant, Long> {

    @Query("""
            SELECT CASE
                WHEN COUNT(plant) > 0 THEN TRUE
                ELSE FALSE END
            FROM Plant plant
            WHERE plant.scientificName = :scientificName""")
    boolean existsByScientificName(@Param("scientificName") String scientificName);
}
