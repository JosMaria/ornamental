package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.dto.news.CreateNewsDTO;
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
    public List<NewsResponseDTO> findAll() {
        log.info("Returning all news");
        return newsRepository.findAll()
                .stream()
                .map(this::entityToDTO)
                .toList();
    }

    @Override
    public NewsResponseDTO findById(Long id) {
        News newsFounded = newsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Item with ID: %s does not exists", id)));
        log.info("Returning news with ID: {}", id);
        return entityToDTO(newsFounded);
    }

    @Override
    public NewsResponseDTO create(CreateNewsDTO createNewsDTO) {
        News newsToPersist = dtoToEntity(createNewsDTO);
        News newsPersisted = newsRepository.save(newsToPersist);
        log.info("Entity with ID {} saved", newsPersisted.getId());
        return entityToDTO(newsPersisted);
    }

    @Override
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
        log.info("Delete news with ID: {}", id);
    }

    private NewsResponseDTO entityToDTO(News news) {
        return NewsResponseDTO.builder()
                .id(news.getId())
                .urlImage(news.getUrlImage())
                .title(news.getTitle())
                .description(news.getDescription())
                .build();
    }

    private News dtoToEntity(CreateNewsDTO dto) {
        return News.builder()
                .urlImage(dto.getUrlImage())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
    }
}
