package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.news.NewsV2;
import org.fdryt.ornamental.dto.alternative.news.NewsRequestDTO;
import org.fdryt.ornamental.dto.alternative.news.NewsResponseDTO;
import org.fdryt.ornamental.repository.NewsJpaRepositoryV2;
import org.fdryt.ornamental.service.NewsServiceV2;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsServiceV2Impl implements NewsServiceV2 {

    private final NewsJpaRepositoryV2 newsJpaRepository;

    @Override
    public NewsResponseDTO createNews(final NewsRequestDTO payload) {
        NewsV2 newsToPersist = toEntityNews(payload);
        NewsV2 newsPersisted = newsJpaRepository.save(newsToPersist);
        log.info("News persisted with its new ID: {}", newsPersisted.getId());
        return toNewsResponseDTO(newsPersisted);
    }

    private NewsV2 toEntityNews(NewsRequestDTO dto) {
        return NewsV2.builder()
                .title(dto.title())
                .content(dto.content())
                .build();
    }

    private NewsResponseDTO toNewsResponseDTO(NewsV2 news) {
        return new NewsResponseDTO(news.getId(), news.getTitle(), news.getContent());
    }
}
