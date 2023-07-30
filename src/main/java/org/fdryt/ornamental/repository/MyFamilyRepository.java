package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.commons.repository.NurseryRepository;
import org.fdryt.ornamental.domain.plant.MyFamily;

import java.util.List;

public interface MyFamilyRepository extends NurseryRepository<MyFamily, Integer> {

    boolean existsByName(String name);

    List<String> getAllNames();
}
