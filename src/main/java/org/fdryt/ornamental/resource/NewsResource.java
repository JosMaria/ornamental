package org.fdryt.ornamental.resource;

import lombok.RequiredArgsConstructor;
import org.fdryt.ornamental.dto.alternative.news.NewsRequestDTO;
import org.fdryt.ornamental.dto.alternative.news.NewsResponseDTO;
import org.fdryt.ornamental.service.NewsServiceV2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/news")
public class NewsResource {

    private final NewsServiceV2 newsService;

    @PostMapping
    public ResponseEntity<NewsResponseDTO> saveNews(@RequestBody NewsRequestDTO payload) {
        return ResponseEntity
                .created(URI.create("/api/v2/news"))
                .body(newsService.createNews(payload));
    }
}
