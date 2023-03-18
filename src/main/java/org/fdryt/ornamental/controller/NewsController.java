package org.fdryt.ornamental.controller;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.news.NewsResponseDTO;
import org.fdryt.ornamental.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/news")
public class NewsController {

    private final NewsService newsService;

    //TODO: endpoint access public
    @GetMapping
    public ResponseEntity<List<NewsResponseDTO>> findAllNews() {
        return ResponseEntity.ok(newsService.findAllNews());
    }
}
