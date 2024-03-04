package org.fdryt.ornamental.resource;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.alternative.news.NewsRequestDTO;
import org.fdryt.ornamental.dto.alternative.news.NewsResponseDTO;
import org.fdryt.ornamental.dto.alternative.news.SingleNewsResponseDTO;
import org.fdryt.ornamental.service.NewsService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/news")
public class NewsResource {

    private final NewsService newsService;

    @PostMapping
    public ResponseEntity<NewsResponseDTO> saveNews(@RequestBody NewsRequestDTO payload) {
        return ResponseEntity
                .created(URI.create("/api/v2/news"))
                .body(newsService.createNews(payload));
    }

    @GetMapping
    public ResponseEntity<Page<NewsResponseDTO>> fetchNews(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "page", defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(newsService.obtainNews(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SingleNewsResponseDTO> fetchNewsByID(@PathVariable("id") String id) {
        return ResponseEntity.ok(newsService.obtainNewsByID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsResponseDTO> updateNewsByID(
            @PathVariable("id") String id, @RequestBody NewsRequestDTO payload) {
        return ResponseEntity.ok(newsService.modifyNewsByID(id, payload));
    }

    @PatchMapping("/{id}/visible")
    public ResponseEntity<String> patchNewsByID(@PathVariable("id") String id) {
        return ResponseEntity.ok(newsService.changeValueIsVisible(id));
    }
}
