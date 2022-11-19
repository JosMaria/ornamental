package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.ClassificationByUtility;
import org.fdryt.ornamental.domain.Identification;
import org.fdryt.ornamental.domain.OrnamentalPlant;
import org.fdryt.ornamental.dto.ProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrnamentalPlantRepository extends JpaRepository<OrnamentalPlant, Long> {

    @Query("""
            SELECT new org.fdryt.ornamental.dto.ProductResponseDTO
                (o.id, i.commonName, i.scientificName, i.family, o.urlImage, o.status)
            FROM OrnamentalPlant o
            INNER JOIN o.identification i
            INNER JOIN i.classificationsByUtility c
            WHERE c.classificationByUtility = :type""")
    Page<ProductResponseDTO> findAllByClassification(@Param("type") ClassificationByUtility type, Pageable pageable);

    /*@Query(nativeQuery = true,
            value = """
                    SELECT o.id, in_conservation, url_image, common_name, family, scientific_name, classification_by_utility
                    FROM ornamental_plants o
                    INNER JOIN (
                        SELECT id, common_name, family, scientific_name, classification_by_utility
                        FROM identifications i
                        INNER JOIN (
                            SELECT identification_id, classification_by_utility
                            FROM identifications_classifications ic
                            INNER JOIN classifications c
                              ON ic.classification_id = c.id AND classification_by_utility = :type) classification
                            ON classification.identification_id = i.id) result
                        ON result.id = o.identification_id""")*/
}
