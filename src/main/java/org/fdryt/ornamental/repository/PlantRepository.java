package org.fdryt.ornamental.repository;

public interface PlantRepository {

    boolean existsByScientificName(String scientificName);
}
