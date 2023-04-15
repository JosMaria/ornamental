package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Integer> {

    Optional<Family> findByName(String name);
}
