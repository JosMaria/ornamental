package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.ClassificationByUtility;
import org.fdryt.ornamental.domain.Plant;
import org.fdryt.ornamental.domain.Status;
import org.fdryt.ornamental.dto.product.ProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer> {

    @Query("""
            SELECT p
            FROM Plant p
            INNER JOIN p.identification i
            INNER JOIN i.classifications c
            WHERE c.utility = :utility""")
    Page<Plant> findAllByIdentificationClassifications(@Param("utility") ClassificationByUtility utility, Pageable pageable);

    @Query("""
            SELECT p
            FROM Plant p
            WHERE p.status = :status""")
    Page<Plant> findAllByStatus(@Param("status") Status status, Pageable pageable);
}
