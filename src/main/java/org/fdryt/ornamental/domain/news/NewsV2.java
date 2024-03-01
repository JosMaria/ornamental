package org.fdryt.ornamental.domain.news;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "news_v2")
public class NewsV2 {

    @Id
    @UuidGenerator
    @Column(updatable = false)
    private String id;

    @Lob
    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createAt;

    private LocalDateTime updatedAt;

    private boolean isVisible;
}

