package org.fdryt.ornamental.repository;

import org.fdryt.ornamental.domain.news.NewsV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsJpaRepositoryV2 extends JpaRepository<NewsV2, String> {
}
