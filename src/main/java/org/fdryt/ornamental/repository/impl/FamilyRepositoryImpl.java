package org.fdryt.ornamental.repository.impl;

import org.fdryt.ornamental.commons.repository.AbstractNurserySqlRepository;
import org.fdryt.ornamental.domain.plant.Family;
import org.fdryt.ornamental.dto.family.FamilyResponseDTO;
import org.fdryt.ornamental.repository.FamilyRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FamilyRepositoryImpl extends AbstractNurserySqlRepository<Family, Integer> implements FamilyRepository {

    protected FamilyRepositoryImpl() {
        super(Family.class);
    }

    @Override
    public boolean existsByName(String name) {
        var jpqlQuery = """
                SELECT
                    CASE WHEN COUNT(f) > 0
                        THEN TRUE
                        ELSE FALSE
                    END
                FROM Family f
                WHERE f.name = :name
                """;

        return em.createQuery(jpqlQuery, Boolean.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<FamilyResponseDTO> getFamilies() {
        return em.createNamedQuery("getIdsAndNamesOfTheFamilies")
                .getResultList();
    }

    @Override
    public Optional<Family> findByName(String name) {
        var jpqlQuery = """
                SELECT f
                FROM Family f
                WHERE f.name = :name
                """;

        Family recordObtained = em.createQuery(jpqlQuery, Family.class)
                .setParameter("name", name)
                .getSingleResult();

        return Optional.ofNullable(recordObtained);
    }
}
