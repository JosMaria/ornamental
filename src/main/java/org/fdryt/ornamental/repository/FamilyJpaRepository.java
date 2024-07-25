package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.plant.Family;
import org.fdryt.ornamental.dto.family.FamilyResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyJpaRepository extends JpaRepository<Family, String> {

    @Query(value = """
        SELECT
            CASE WHEN COUNT(*) > 0
                 THEN TRUE
                 ELSE FALSE
            END AS exists
        FROM families family
        WHERE family.name = :name
    """, nativeQuery = true)
    boolean existsByName(@Param("name") String name);

    @Query(name = "findAllFamilies", nativeQuery = true)
    List<FamilyResponseDTO> findAllFamilies();
}
