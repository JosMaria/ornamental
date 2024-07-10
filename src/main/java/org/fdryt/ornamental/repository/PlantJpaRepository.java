package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.plant.Plant;
import org.fdryt.ornamental.dto.plant.SimpleInfoPlantResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantJpaRepository extends JpaRepository<Plant, Integer> {

    @Query("""
        SELECT
            CASE WHEN COUNT(p) > 0
                THEN TRUE
                ELSE FALSE
            END
        FROM Plant p
        WHERE p.fundamentalData.commonName = :commonName
    """)
    boolean existsByCommonName(@Param("commonName") String commonName);

    @Query("""
            SELECT NEW org.fdryt.ornamental.dto.plant.SimpleInfoPlantResponseDTO(
                p.id,
                p.fundamentalData.commonName)
            FROM Plant p
            """)
    List<SimpleInfoPlantResponseDTO> findAllSimpleInfoPlant();
}
