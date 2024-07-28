package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.plant.Image;
import org.fdryt.ornamental.dto.image.ImageMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {

    @Query(value = """
        SELECT id
        FROM images
        WHERE plant_id = :plantId
        LIMIT 1
    """, nativeQuery = true)
    List<String> findByPlantId(@Param("plantId") Long plantId);

    @Query(name = "fetchAllByPlantId", nativeQuery = true)
    List<ImageMapping> fetchAllByPlantId(@Param("plantId") String id);
}
