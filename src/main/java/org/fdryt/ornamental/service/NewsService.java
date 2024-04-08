package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.news.NewsInfoStateResponseDTO;
import org.fdryt.ornamental.dto.news.NewsRequestDTO;
import org.fdryt.ornamental.dto.news.NewsResponseDTO;
import org.fdryt.ornamental.dto.news.SingleNewsResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NewsService {


    NewsResponseDTO createNews(NewsRequestDTO payload);

    Page<NewsResponseDTO> obtainNewsVisible(int page, int size);

    List<NewsInfoStateResponseDTO> obtainAllNews();

    SingleNewsResponseDTO obtainNewsByID(String id);

    NewsResponseDTO modifyNewsByID(String id, NewsRequestDTO payload);

    String changeValueIsVisible(String id);
}
