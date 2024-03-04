package org.fdryt.ornamental.repository;

import jakarta.transaction.Transactional;
import org.fdryt.ornamental.domain.news.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface NewsJpaRepository extends JpaRepository<News, String> {

    @Transactional
    @Query("""
        SELECT news
        FROM News news
        WHERE news.isVisible = TRUE
    """)
    Page<News> findAllNewsVisible(Pageable pageable);

    @Transactional
    @Modifying
    @Query("""
        UPDATE News news
        SET news.isVisible = :newValue
        WHERE news.id = :id
    """)
    int changeValueIsVisibleGivenID(@Param("id") String id, @Param("newValue") boolean newValue);
}
