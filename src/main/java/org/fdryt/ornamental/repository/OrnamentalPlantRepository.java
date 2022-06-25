package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.OrnamentalPlant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrnamentalPlantRepository extends JpaRepository<OrnamentalPlant, Long> {

}
