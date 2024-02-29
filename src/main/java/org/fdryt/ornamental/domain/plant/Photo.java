package org.fdryt.ornamental.domain.plant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "photos")
public class Photo {

    @Id
    @UuidGenerator
    @Column(name = "id", unique = true, updatable = false)
    private String id;

    private String url;
}
