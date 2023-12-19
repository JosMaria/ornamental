package org.fdryt.ornamental.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.news.CreateNewsDTO;
import org.fdryt.ornamental.dto.news.NewsResponseDTO;
import org.fdryt.ornamental.dto.news.UpdateNewsDTO;
import org.fdryt.ornamental.service.NewsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin(origins = {"http://localhost:5173/", "https://magnificent-daifuku-409cab.netlify.app/"}, allowedHeaders = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/news")
public class NewsController {

    private final NewsService newsService;

    @PostMapping
//    @PreAuthorize("hasAuthority('news:create')")
    public ResponseEntity<NewsResponseDTO> create(@RequestBody @Valid final CreateNewsDTO createNewsDTO) {
        return new ResponseEntity<>(newsService.create(createNewsDTO), CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<NewsResponseDTO>> findAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(newsService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponseDTO> findById(@PathVariable("id") final Integer id) {
        return ResponseEntity.ok(newsService.findById(id));
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('news:delete')")
    public ResponseEntity<Void> deleteById(@PathVariable("id") final Integer id) {
        newsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('news:update')")
    public ResponseEntity<NewsResponseDTO> update(@PathVariable("id") final Integer id, @RequestBody @Valid final UpdateNewsDTO updateNewsDTO) {
        return ResponseEntity.ok(newsService.update(id, updateNewsDTO));
    }

    @PatchMapping("/{id}")
//    @PreAuthorize("hasAuthority('news_fields:update')")
    public ResponseEntity<NewsResponseDTO> updateByFields(@PathVariable("id") Integer id, @RequestBody final Map<String, Object> fields) {
        return ResponseEntity.ok(newsService.updateByFields(id, fields));
    }
}
