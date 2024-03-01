package org.fdryt.ornamental.repository;

import jakarta.transaction.Transactional;
import org.fdryt.ornamental.domain.news.NewsV2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface NewsJpaRepositoryV2 extends JpaRepository<NewsV2, String> {

    @Transactional
    @Query("""
        SELECT news
        FROM NewsV2 news
        WHERE news.isVisible = TRUE
    """)
    Page<NewsV2> findAllNewsVisible(Pageable pageable);

    @Transactional
    @Modifying
    @Query("""
        UPDATE NewsV2 news
        SET news.isVisible = :newValue
        WHERE news.id = :id
    """)
    int changeValueIsVisibleGivenID(@Param("id") String id, @Param("newValue") boolean newValue);
}
