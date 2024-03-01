package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.alternative.news.NewsRequestDTO;
import org.fdryt.ornamental.dto.alternative.news.NewsResponseDTO;
import org.springframework.data.domain.Page;

public interface NewsServiceV2 {


    NewsResponseDTO createNews(NewsRequestDTO payload);

    Page<NewsResponseDTO> obtainNews(int page, int size);

    NewsResponseDTO obtainNewsByID(String id);

    NewsResponseDTO modifyNewsByID(String id, NewsRequestDTO payload);
}
