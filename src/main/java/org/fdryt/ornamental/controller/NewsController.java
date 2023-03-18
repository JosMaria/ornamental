package org.fdryt.ornamental.controller;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.news.CreateNewsDTO;
import org.fdryt.ornamental.dto.news.NewsResponseDTO;
import org.fdryt.ornamental.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/news")
public class NewsController {

    private final NewsService newsService;

    // TODO: endpoint access public
    @GetMapping
    public ResponseEntity<List<NewsResponseDTO>> findAll() {
        return ResponseEntity.ok(newsService.findAll());
    }

    // TODO: endpoint access public
    @GetMapping("{id}")
    public ResponseEntity<NewsResponseDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(newsService.findById(id));
    }

    // TODO: endpoint private, only access with authorization
    @PostMapping
    public ResponseEntity<NewsResponseDTO> create(@RequestBody CreateNewsDTO createNewsDTO) {
        return new ResponseEntity<>(newsService.create(createNewsDTO), CREATED);
    }

    // TODO: endpoint private, only access with authorization
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        newsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
