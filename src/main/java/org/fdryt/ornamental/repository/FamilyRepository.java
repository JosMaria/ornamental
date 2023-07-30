package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.commons.repository.NurseryRepository;
import org.fdryt.ornamental.domain.plant.MyFamily;

import java.util.List;
import java.util.Optional;

public interface FamilyRepository extends NurseryRepository<MyFamily, Integer> {

    boolean existsByName(String name);

    List<String> getAllNames();

    Optional<MyFamily> findByName(String name);
}
