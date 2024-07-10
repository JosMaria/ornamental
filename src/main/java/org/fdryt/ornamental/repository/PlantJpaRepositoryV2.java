package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.plant.alternative.PlantV3;
import org.fdryt.ornamental.dto.catalog.PlantCardDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantJpaRepositoryV2 extends JpaRepository<PlantV3, String> {

    @Query(name = "findAllPlantCards", nativeQuery = true)
    List<PlantCardDTO> findAllPlantCards(@Param("limit") int limit, @Param("offset") int offset);

    @Query(name = "findPlantCardsByClassification", nativeQuery = true)
    List<PlantCardDTO> findPlantCardsByClassification(
            @Param("limit") int limit,
            @Param("offset") int offset,
            @Param("classification") String classification
    );

    @Query(value = """
        SELECT COUNT(*)
        FROM plants_v3 plant
        INNER JOIN plantv3_classifications c
               ON plant.id = c.plantv3_id
        WHERE c.classifications = :classification
    """, nativeQuery = true)
    long countByClassification(@Param("classification") String classification);
}
