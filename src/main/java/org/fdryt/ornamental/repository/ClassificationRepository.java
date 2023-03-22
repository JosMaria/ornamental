package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.Classification;
import org.fdryt.ornamental.domain.ClassificationByUtility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Long> {

    @Query("""
            SELECT c.utility
            FROM Classification c"""
    )
    Collection<ClassificationByUtility> findClassificationsByUtility();
}
