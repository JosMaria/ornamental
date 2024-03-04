package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.alternative.news.NewsRequestDTO;
import org.fdryt.ornamental.dto.alternative.news.NewsResponseDTO;
import org.fdryt.ornamental.dto.alternative.news.SingleNewsResponseDTO;
import org.springframework.data.domain.Page;

public interface NewsServiceV2 {


    NewsResponseDTO createNews(NewsRequestDTO payload);

    Page<NewsResponseDTO> obtainNews(int page, int size);

    SingleNewsResponseDTO obtainNewsByID(String id);

    NewsResponseDTO modifyNewsByID(String id, NewsRequestDTO payload);

    String changeValueIsVisible(String id);
}
