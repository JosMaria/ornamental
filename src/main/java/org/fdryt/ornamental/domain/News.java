package org.fdryt.ornamental.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "news_sequence")
    @SequenceGenerator(name = "news_sequence", sequenceName = "news_sequence", allocationSize = 1)
    private Long id;

    private String urlImage;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 2000)
    private String description;

    public News(String urlImage, String title, String description) {
        this.urlImage = urlImage;
        this.title = title;
        this.description = description;
    }
}
