package org.fdryt.ornamental.repository.impl;

import org.fdryt.ornamental.commons.repository.AbstractNurserySqlRepository;
import org.fdryt.ornamental.domain.plant.Plant;
import org.fdryt.ornamental.repository.PlantRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PlantRepositoryImpl extends AbstractNurserySqlRepository<Plant, Integer> implements PlantRepository {

    public PlantRepositoryImpl() {
        super(Plant.class);
    }

    @Override
    public boolean existsByCommonName(String commonName) {
        var jpqlQuery = """
            SELECT CASE WHEN COUNT(p) > 0
                THEN TRUE
                ELSE FALSE
                END
            FROM Plant p
            WHERE p.fundamentalData.commonName = :commonName
            """;

        return em.createQuery(jpqlQuery, Boolean.class)
                .setParameter("commonName", commonName)
                .getSingleResult();
    }
}
