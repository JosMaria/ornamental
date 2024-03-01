package org.fdryt.ornamental.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.news.NewsV2;
import org.fdryt.ornamental.dto.alternative.news.NewsRequestDTO;
import org.fdryt.ornamental.dto.alternative.news.NewsResponseDTO;
import org.fdryt.ornamental.repository.NewsJpaRepositoryV2;
import org.fdryt.ornamental.service.NewsServiceV2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    @Override
    public Page<NewsResponseDTO> obtainNews(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createAt"));
        Page<NewsResponseDTO> newsObtained = newsJpaRepository.findAllNewsVisible(pageRequest)
                .map(this::toNewsResponseDTO);
        log.info("Obtained news of the number page: {} with size: {}", page, size);
        return newsObtained;
    }

    @Override
    public NewsResponseDTO obtainNewsByID(final String id) {
        NewsV2 newsObtained = throwExceptionIfNewsNotFound(id);
        log.info("Fetched news with ID: {}", newsObtained.getId());
        return toNewsResponseDTO(newsObtained);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public NewsResponseDTO modifyNewsByID(final String id, final NewsRequestDTO payload) {
        NewsV2 newsObtained = throwExceptionIfNewsNotFound(id);
        newsObtained.setTitle(payload.title());
        newsObtained.setContent(payload.content());
        newsObtained.setUpdatedAt(LocalDateTime.now());
        log.info("updated news with ID: {}", id);
        return toNewsResponseDTO(newsObtained);
    }

    @Override
    public String changeValueIsVisible(final String id) {
        NewsV2 newsObtained = throwExceptionIfNewsNotFound(id);
        int countRowUpdated = newsJpaRepository.changeValueIsVisibleGivenID(id, !newsObtained.isVisible());
        if (countRowUpdated > 0) {
            String response = "The news with ID: %s: changed its state".formatted(id);
            log.info(response);
            return response;
        }
        return "None of the news was updated.";
    }

    private NewsV2 throwExceptionIfNewsNotFound(String id) {
        return newsJpaRepository.findById(id).orElseThrow(() -> {
            String message = "News with ID: %s does not founded.".formatted(id);
            log.info(message);
            return new IllegalArgumentException(message);
        });
    }

    private NewsV2 toEntityNews(NewsRequestDTO dto) {
        return NewsV2.builder()
                .title(dto.title())
                .content(dto.content())
                .createAt(LocalDateTime.now())
                .isVisible(true)
                .build();
    }

    private NewsResponseDTO toNewsResponseDTO(NewsV2 news) {
        return new NewsResponseDTO(news.getId(), news.getTitle(), news.getContent(), news.getCreateAt(), news.getUpdatedAt());
    }
}
