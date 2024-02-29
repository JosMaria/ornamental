package org.fdryt.ornamental.service;

import org.fdryt.ornamental.dto.alternative.news.NewsRequestDTO;
import org.fdryt.ornamental.dto.alternative.news.NewsResponseDTO;

public interface NewsServiceV2 {


    NewsResponseDTO createNews(NewsRequestDTO payload);
}
