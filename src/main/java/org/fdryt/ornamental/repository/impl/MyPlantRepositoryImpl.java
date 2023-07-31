package org.fdryt.ornamental.repository.impl;

import jakarta.persistence.EntityManager;
import org.fdryt.ornamental.commons.repository.AbstractNurserySqlRepository;
import org.fdryt.ornamental.domain.plant.MyPlant;
import org.fdryt.ornamental.repository.MyPlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MyPlantRepositoryImpl extends AbstractNurserySqlRepository<MyPlant, Integer> implements MyPlantRepository {

    public MyPlantRepositoryImpl(EntityManager em) {
        super(em, MyPlant.class);
    }

    @Override
    public boolean existsByCommonName(String commonName) {
        var jpqlQuery = """
            SELECT CASE WHEN COUNT(p) > 0
                THEN TRUE
                ELSE FALSE
                END
            FROM MyPlant p
            WHERE p.fundamentalData.commonName = :commonName
            """;

        return em.createQuery(jpqlQuery, Boolean.class)
                .setParameter("commonName", commonName)
                .getSingleResult();
    }
}
