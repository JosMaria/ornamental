package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
}
