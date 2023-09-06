package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.commons.repository.NurseryRepository;
import org.fdryt.ornamental.domain.plant.Family;

import java.util.List;
import java.util.Optional;

public interface FamilyRepository extends NurseryRepository<Family, Integer> {

    boolean existsByName(String name);

    List<String> getAllNames();

    Optional<Family> findByName(String name);
}
