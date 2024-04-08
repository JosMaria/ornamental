package org.fdryt.ornamental.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.news.News;
import org.fdryt.ornamental.dto.news.NewsInfoStateResponseDTO;
import org.fdryt.ornamental.dto.news.NewsRequestDTO;
import org.fdryt.ornamental.dto.news.NewsResponseDTO;
import org.fdryt.ornamental.dto.news.SingleNewsResponseDTO;
import org.fdryt.ornamental.exception.NewsNotAvailableException;
import org.fdryt.ornamental.exception.NewsNotFoundException;
import org.fdryt.ornamental.repository.NewsJpaRepository;
import org.fdryt.ornamental.service.NewsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsJpaRepository newsJpaRepository;

    @Override
    public NewsResponseDTO createNews(final NewsRequestDTO payload) {
        News newsToPersist = toEntityNews(payload);
        News newsPersisted = newsJpaRepository.save(newsToPersist);
        log.info("News persisted with its new ID: {}", newsPersisted.getId());
        return toNewsResponseDTO(newsPersisted);
    }

    @Override
    public Page<NewsResponseDTO> obtainNewsVisible(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<NewsResponseDTO> newsObtained = newsJpaRepository.findAllNewsVisible(pageRequest)
                .map(this::toNewsResponseDTO);
        log.info("Obtained news of the number page: {} with size: {}", page, size);
        return newsObtained;
    }

    @Override
    public List<NewsInfoStateResponseDTO> obtainAllNews() {
        List<News> newsObtained = newsJpaRepository.findAll();
        log.info("Fetched all news.");
        return newsObtained.stream()
                .map(mapperNewsInfoStateResponseDTO)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public SingleNewsResponseDTO obtainNewsByID(final String id) {
        News newsObtained = throwExceptionIfNewsNotFound(id);
        if (!newsObtained.isVisible()) {
            log.info("The news with ID: %s {} was not shown.", id);
            throw new NewsNotAvailableException("This news is not available for now");
        }

        log.info("Fetched news with ID: {}", newsObtained.getId());
        return toSingleNewsResponseDTO(newsObtained);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public NewsResponseDTO modifyNewsByID(final String id, final NewsRequestDTO payload) {
        News newsObtained = throwExceptionIfNewsNotFound(id);
        if (payload.title() != null) {
            newsObtained.setTitle(payload.title());
        }

        if (payload.description() != null) {
            newsObtained.setDescription(payload.description());
        }

        if (payload.content() != null) {
            newsObtained.setContent(payload.content());
        }

        newsObtained.setUpdatedAt(LocalDateTime.now());
        log.info("updated news with ID: {}", id);
        return toNewsResponseDTO(newsObtained);
    }

    @Override
    public String changeValueIsVisible(final String id) {
        News newsObtained = throwExceptionIfNewsNotFound(id);
        int countRowUpdated = newsJpaRepository.changeValueIsVisibleGivenID(id, !newsObtained.isVisible());
        if (countRowUpdated > 0) {
            String response = "The news with ID: %s: changed its state".formatted(id);
            log.info(response);
            return response;
        }

        return "None of the news was updated.";
    }

    private News throwExceptionIfNewsNotFound(String id) {
        return newsJpaRepository.findById(id)
                .orElseThrow(() -> {
                    var exception = new NewsNotFoundException(id);
                    log.info(exception.getMessage());
                    return exception;
                });
    }

    private News toEntityNews(NewsRequestDTO dto) {
        return News.builder()
                .title(dto.title())
                .description(dto.description())
                .content(dto.content())
                .createdAt(LocalDateTime.now())
                .isVisible(true)
                .build();
    }

    private NewsResponseDTO toNewsResponseDTO(News news) {
        return new NewsResponseDTO(
                news.getId(),
                news.getTitle(),
                news.getDescription(),
                "https://i.stack.imgur.com/Of2w5.jpg",
                news.getCreatedAt(),
                news.getUpdatedAt(),
                "https://lh3.googleusercontent.com/-75PEaiU9U3s/VOIS2XRjj1I/AAAAAAAAA8g/hrSIcbRe89s/s2048/cool-and-stylish-profile-pictures-for-facebook-for-girls-2015-cool-and-stylish-profile-pictures-for-facebook-for-girls-2014-1931-AZ.jpg",
                "Jose Maria Aguilar Chambi");
    }

    private SingleNewsResponseDTO toSingleNewsResponseDTO(News news) {
        return new SingleNewsResponseDTO(
                news.getId(),
                news.getTitle(),
                news.getDescription(),
                news.getContent(),
                "https://i.stack.imgur.com/Of2w5.jpg",
                news.getCreatedAt(),
                news.getUpdatedAt(),
                "https://lh3.googleusercontent.com/-75PEaiU9U3s/VOIS2XRjj1I/AAAAAAAAA8g/hrSIcbRe89s/s2048/cool-and-stylish-profile-pictures-for-facebook-for-girls-2015-cool-and-stylish-profile-pictures-for-facebook-for-girls-2014-1931-AZ.jpg",
                "Jose Maria Aguilar Chambi"

        );
    }

    private final Function<News, NewsInfoStateResponseDTO> mapperNewsInfoStateResponseDTO = news ->
        new NewsInfoStateResponseDTO(
                news.getId(),
            news.getTitle(),
            news.getDescription(),
            news.isVisible(),
            news.getCreatedAt()
        );
}
