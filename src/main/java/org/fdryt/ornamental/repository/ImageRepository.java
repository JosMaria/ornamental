package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.plant.alternative.ImageV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageV2, Long> {

}
