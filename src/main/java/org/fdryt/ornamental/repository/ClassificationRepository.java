package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.Classification;
import org.fdryt.ornamental.domain.ClassificationByUtility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Integer> {

    @Query("""
            SELECT c.utility
            FROM Classification c"""
    )
    Set<ClassificationByUtility> findAllByUtility();

    Optional<Classification> findByUtility(ClassificationByUtility utility);
}
