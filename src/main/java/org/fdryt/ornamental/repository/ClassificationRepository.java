package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.Classification;
import org.fdryt.ornamental.domain.ClassificationByUtility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Long> {

    @Query("""
            SELECT classification
            FROM Classification classification
            WHERE classification.typeClassification = :type""")
    Optional<Classification> findClassificationByTypeClassification(@Param("type") ClassificationByUtility type);
}*/
