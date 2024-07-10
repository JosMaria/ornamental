package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.plant.alternative.FamilyV2;
import org.fdryt.ornamental.dto.alternative.FamilyResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyJpaRepositoryV2 extends JpaRepository<FamilyV2, String> {

    @Query(value = """
        SELECT
            CASE WHEN COUNT(*) > 0
                 THEN TRUE
                 ELSE FALSE
            END AS exists
        FROM families_v2 family
        WHERE family.name = :name
    """, nativeQuery = true)
    boolean existsByName(@Param("name") String name);

    @Query(name = "findAllFamilies", nativeQuery = true)
    List<FamilyResponseDTO> findAllFamilies();
}
