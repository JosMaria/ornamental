package org.fdryt.ornamental.repository.impl;

import jakarta.persistence.EntityManager;
import org.fdryt.ornamental.commons.repository.AbstractNurserySqlRepository;
import org.fdryt.ornamental.domain.plant.MyFamily;
import org.fdryt.ornamental.repository.MyFamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MyFamilyRepositoryImpl extends AbstractNurserySqlRepository<MyFamily, Integer> implements MyFamilyRepository {

    @Autowired
    protected MyFamilyRepositoryImpl(EntityManager em) {
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
            WHERE f.name = :name""";

        return em.createQuery(jpqlQuery, Boolean.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
