package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.Identification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentificationRepository extends JpaRepository<Identification, Integer> {
}
