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
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    public NewsResponseDTO findById(Integer id) {
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
    public void deleteById(Integer id) {
        newsRepository.deleteById(id);
        log.info("Delete news with ID: {}", id);
    }

    @Transactional
    @Override
    public NewsResponseDTO update(Integer id, UpdateNewsDTO updateNewsDTO) {
        News newsFounded = findByIdOrThrowException(id);
        newsFounded.setUrlImage(updateNewsDTO.getUrlImage());
        newsFounded.setTitle(updateNewsDTO.getTitle());
        newsFounded.setDescription(updateNewsDTO.getDescription());
        log.info("Updated news with ID: {}", id);
        return entityToDTO(newsFounded);
    }

    @Transactional
    @Override
    public NewsResponseDTO updateByFields(Integer id, Map<String, Object> fields) {
        News newsFounded = findByIdOrThrowException(id);
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(News.class, key);

            if (field != null && canChangeField(field.getName())) {
                // TODO: verify type of field example Long to Int to ID
                //Preconditions.checkArgument(field.getType() == String.class, "");
                field.setAccessible(true);
                ReflectionUtils.setField(field, newsFounded, value);
            }
        });
        return entityToDTO(newsFounded);
    }

    private boolean canChangeField(String fieldName) {
        List<String> fieldAvailableToChange = Arrays.asList("urlImage", "title", "description");
        return fieldAvailableToChange.contains(fieldName);
    }

    private News findByIdOrThrowException(Integer id) {
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