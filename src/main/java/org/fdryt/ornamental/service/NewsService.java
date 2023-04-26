package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.news.CreateNewsDTO;
import org.fdryt.ornamental.dto.news.NewsResponseDTO;
import org.fdryt.ornamental.dto.news.UpdateNewsDTO;

import java.util.List;
import java.util.Map;

public interface NewsService {

    NewsResponseDTO create(CreateNewsDTO createNewsDTO);

    List<NewsResponseDTO> findAll();

    NewsResponseDTO findById(Integer id);

    void deleteById(Integer id);

    NewsResponseDTO update(Integer id, UpdateNewsDTO updateNewsDTO);

    NewsResponseDTO updateByFields(Integer id, Map<String, Object> fields);
}
