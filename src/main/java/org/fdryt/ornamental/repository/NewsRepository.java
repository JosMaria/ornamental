package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.news.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Integer> {
}
