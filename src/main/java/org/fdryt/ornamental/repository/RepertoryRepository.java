package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.plant.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepertoryRepository extends JpaRepository<Plant, String> {}
