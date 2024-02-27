package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.plant.alternative.FamilyV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyJpaRepositoryV2 extends JpaRepository<FamilyV2, String> {

    @Query("""
        SELECT
            CASE WHEN COUNT(f) > 0
                THEN TRUE
                ELSE FALSE
            END
        FROM FamilyV2 f
        WHERE f.name = :name
    """)
    boolean existsByName(@Param("name") String name);
}
