package org.fdryt.ornamental.service;

import org.fdryt.ornamental.controller.CreateNewsDTO;
import org.fdryt.ornamental.dto.news.NewsResponseDTO;

import java.util.List;

public interface NewsService {

    List<NewsResponseDTO> findAllNews();

    NewsResponseDTO findNewsById(Long id);

    NewsResponseDTO createNews(CreateNewsDTO createNewsDTO);
}
