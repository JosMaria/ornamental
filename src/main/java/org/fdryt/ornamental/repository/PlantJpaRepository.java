package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.plant.Plant;
import org.fdryt.ornamental.dto.catalog.PlantCardDTO;
import org.fdryt.ornamental.dto.repertory.ItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantJpaRepository extends JpaRepository<Plant, Long> {

    boolean existsByCommonName(String commonName);

    @Query(name = "findAllItems", nativeQuery = true)
    List<ItemDTO> findAllItems();

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
        FROM plants plant
        INNER JOIN plant_classifications c
               ON plant.id = c.plant_id
        WHERE c.classifications = :classification
    """, nativeQuery = true)
    long countByClassification(@Param("classification") String classification);
}
