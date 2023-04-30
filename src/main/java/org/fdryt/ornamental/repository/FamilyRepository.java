package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Integer> {

    Optional<Family> findByName(String name);

    @Query("""
            SELECT f.name
            FROM Family f""")
    Set<String> findAllNames();

    @Query("""
            SELECT CASE WHEN COUNT(f) > 0
                THEN TRUE
                ELSE FALSE END
            FROM Family f
            WHERE f.name = :name""")
    boolean existsByName(@Param("name") String name);
}
