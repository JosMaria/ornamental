package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.news.CreateNewsDTO;
import org.fdryt.ornamental.dto.news.NewsResponseDTO;
import org.fdryt.ornamental.dto.news.UpdateNewsDTO;

import java.util.List;

public interface NewsService {

    List<NewsResponseDTO> findAll();

    NewsResponseDTO findById(Long id);

    NewsResponseDTO create(CreateNewsDTO createNewsDTO);

    void deleteById(Long id);

    NewsResponseDTO update(Long id, UpdateNewsDTO updateNewsDTO);
}
