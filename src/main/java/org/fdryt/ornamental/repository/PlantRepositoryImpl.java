package org.fdryt.ornamental.repository;

import org.springframework.stereotype.Repository;

@Repository
public class PlantRepositoryImpl implements PlantRepository{

    @Override
    public boolean existsByScientificName(String scientificName) {
        return true;
    }
}
