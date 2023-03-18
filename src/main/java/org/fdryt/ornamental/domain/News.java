package org.fdryt.ornamental.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class News {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "news_generator")
    @SequenceGenerator(name = "news_generator", sequenceName = "news_generator")
    private Long id;

    private String urlImage;
    private String title;

    @Column(length = 2000)
    private String description;

    public News(String urlImage, String title, String description) {
        this.urlImage = urlImage;
        this.title = title;
        this.description = description;
    }
}
