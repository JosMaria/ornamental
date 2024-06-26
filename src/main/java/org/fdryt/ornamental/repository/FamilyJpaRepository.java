package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.plant.Family;
import org.fdryt.ornamental.dto.family.FamilyResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface FamilyJpaRepository extends JpaRepository<Family, Integer> {

    @Query("""
        SELECT
            CASE WHEN COUNT(f) > 0
                THEN TRUE
                ELSE FALSE
            END
        FROM Family f
        WHERE f.name = :name
    """)
    boolean existsByName(@Param("name") String name);

    Optional<Family> findByName(String name);
}
