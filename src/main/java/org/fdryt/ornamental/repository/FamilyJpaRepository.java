package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.plant.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}
