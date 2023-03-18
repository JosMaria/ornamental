package org.fdryt.ornamental.controller;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.news.NewsResponseDTO;
import org.fdryt.ornamental.service.NewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

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

    //TODO: endpoint access public
    @GetMapping("{id}")
    public ResponseEntity<NewsResponseDTO> findNewsById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(newsService.findNewsById(id));
    }

    @PostMapping
    public ResponseEntity<NewsResponseDTO> createNews(@RequestBody CreateNewsDTO createNewsDTO) {
        return new ResponseEntity<>(newsService.createNews(createNewsDTO), CREATED);
    }
}
