package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.news.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsJpaRepository extends JpaRepository<News, Integer> {
}
