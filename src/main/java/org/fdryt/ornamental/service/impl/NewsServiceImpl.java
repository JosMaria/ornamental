package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.News;
import org.fdryt.ornamental.dto.news.NewsResponseDTO;
import org.fdryt.ornamental.repository.NewsRepository;
import org.fdryt.ornamental.service.NewsService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Override
    public List<NewsResponseDTO> findAllNews() {
        log.info("Returning all news");
        return newsRepository.findAll()
                .stream()
                .map(this::entityToDTO)
                .toList();
    }

    private NewsResponseDTO entityToDTO(News news) {
        return NewsResponseDTO.builder()
                .id(news.getId())
                .urlImage(news.getUrlImage())
                .title(news.getTitle())
                .description(news.getDescription())
                .build();

    }
}
