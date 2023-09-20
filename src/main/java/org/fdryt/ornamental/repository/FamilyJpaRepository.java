package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.plant.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyJpaRepository extends JpaRepository<Family, Integer> {


}
