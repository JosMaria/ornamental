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
}
