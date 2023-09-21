package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.plant.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}
