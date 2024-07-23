package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.plant.Image;
import org.fdryt.ornamental.dto.image.ImageMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query(name = "fetchAllByPlantId", nativeQuery = true)
    List<ImageMapping> fetchAllByPlantId(@Param("plantId") String id);
}
