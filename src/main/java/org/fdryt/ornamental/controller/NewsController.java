package org.fdryt.ornamental.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.news.CreateNewsDTO;
import org.fdryt.ornamental.dto.news.NewsResponseDTO;
import org.fdryt.ornamental.dto.news.UpdateNewsDTO;
import org.fdryt.ornamental.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin(origins = "http://localhost:5173/", allowedHeaders = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/news")
public class NewsController {

    private final NewsService newsService;

    // TODO: endpoint private, only access with authorization
    @PostMapping
    public ResponseEntity<NewsResponseDTO> create(@RequestBody @Valid final CreateNewsDTO createNewsDTO) {
        return new ResponseEntity<>(newsService.create(createNewsDTO), CREATED);
    }

    // TODO: endpoint access public
    @GetMapping
    public ResponseEntity<List<NewsResponseDTO>> findAll() {
        return ResponseEntity.ok(newsService.findAll());
    }

    // TODO: endpoint access public
    @GetMapping("{id}")
    public ResponseEntity<NewsResponseDTO> findById(@PathVariable("id") final Integer id) {
        return ResponseEntity.ok(newsService.findById(id));
    }

    // TODO: endpoint private, only access with authorization
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") final Integer id) {
        newsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // TODO: endpoint private, only access with authorization
    @PutMapping("{id}")
    public ResponseEntity<NewsResponseDTO> update(@PathVariable("id") final Integer id, @RequestBody @Valid final UpdateNewsDTO updateNewsDTO) {
        return ResponseEntity.ok(newsService.update(id, updateNewsDTO));
    }

    // TODO: endpoint private, only access with authorization
    @PatchMapping("{id}")
    public ResponseEntity<NewsResponseDTO> updateByFields(@PathVariable("id") Integer id, @RequestBody final Map<String, Object> fields) {
        return ResponseEntity.ok(newsService.updateByFields(id, fields));
    }
}
