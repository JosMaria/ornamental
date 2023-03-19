package org.fdryt.ornamental.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.domain.News;
import org.fdryt.ornamental.dto.news.CreateNewsDTO;
import org.fdryt.ornamental.dto.news.NewsResponseDTO;
import org.fdryt.ornamental.dto.news.UpdateNewsDTO;
import org.fdryt.ornamental.problem.exception.DomainNotFoundException;
import org.fdryt.ornamental.repository.NewsRepository;
import org.fdryt.ornamental.service.NewsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        News newsFounded = findByIdOrThrowException(id);
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

    @Transactional
    @Override
    public NewsResponseDTO update(Long id, UpdateNewsDTO updateNewsDTO) {
        News newsFounded = findByIdOrThrowException(id);
        newsFounded.setUrlImage(updateNewsDTO.getUrlImage());
        newsFounded.setTitle(updateNewsDTO.getTitle());
        newsFounded.setDescription(updateNewsDTO.getDescription());
        log.info("Updated news with ID: {}", id);
        return entityToDTO(newsFounded);
    }

    private News findByIdOrThrowException(Long id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new DomainNotFoundException(News.class, id));
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
