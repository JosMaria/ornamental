package org.fdryt.ornamental.repository.impl;

import jakarta.persistence.EntityManager;
import org.fdryt.ornamental.commons.repository.AbstractNurserySqlRepository;
import org.fdryt.ornamental.domain.plant.MyFamily;
import org.fdryt.ornamental.repository.FamilyRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FamilyRepositoryImpl extends AbstractNurserySqlRepository<MyFamily, Integer> implements FamilyRepository {

    protected FamilyRepositoryImpl(EntityManager em) {
        super(em, MyFamily.class);
    }

    @Override
    public boolean existsByName(String name) {
        var jpqlQuery = """
                SELECT
                    CASE WHEN COUNT(f) > 0
                        THEN TRUE
                        ELSE FALSE
                    END
                FROM MyFamily f
                WHERE f.name = :name
                """;

        return em.createQuery(jpqlQuery, Boolean.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<String> getAllNames() {
        var jpqlQuery = """
                SELECT f.name
                FROM MyFamily f
                """;

        return em.createQuery(jpqlQuery, String.class)
                .getResultList();
    }

    @Override
    public Optional<MyFamily> findByName(String name) {
        var jpqlQuery = """
                SELECT f
                FROM MyFamily f
                WHERE f.name = :name
                """;

        MyFamily recordObtained = em.createQuery(jpqlQuery, MyFamily.class)
                .setParameter("name", name)
                .getSingleResult();

        return Optional.of(recordObtained);
    }
}
